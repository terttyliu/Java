package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pjliu
 */
public class ThreadPoolExecutorTest {
    @Test
    /**
     * 四种拒绝策略：
     *     一、AbortPolicy              超过承载时（最大线程池数+阻塞队列长度）抛出异常
     *     二、CallerRunsPolicy()       超过承载时 由调用线程执行任务。(会先执行任务)
     *     三、DiscardPolicy()          超过承载时 放弃任务，不抛出异常
     *     四、DiscardOldestPolicy()    超过承载时，尝试去和最早的竞争,也不会抛出异常!
     */
    public void test() throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 16; i++) {
            final int finalI=i;
            poolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"=>"+finalI);
            });
        }
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+"=>睡眠结束");
    }
}
