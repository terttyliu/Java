package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 双端队列 ——> 工作窃取
 *
 * @author pjliu
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();
        test1();
    }

    public static void test() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> submit = pool.submit(new ForkJoinTest(1L, 10_0000_0000L));
        Long aLong = submit.get();
        long end = System.currentTimeMillis();
        System.out.println(aLong + " 用时" + (end - start));
    }

    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(sum + " 时间:" + (end - start));
    }

    //求和计算(一般,ForkJoin,Stream)
    private Long start;
    private Long end;
    private Long temp = 10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < temp) {
            long res = 0;
            for (long i = start; i <= end; i++) {
                res += i;
            }
            return res;
        } else {
            long middle = (end + start) / 2;
            ForkJoinTask<Long> task1 = new ForkJoinTest(start, middle);
            task1.fork();
            ForkJoinTask<Long> task2 = new ForkJoinTest(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
