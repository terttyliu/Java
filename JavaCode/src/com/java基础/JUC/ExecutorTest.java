package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pjliu
 */
public class ExecutorTest {
    @Test
    public void test() throws InterruptedException {
//        ExecutorService pool = Executors.newSingleThreadExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10000; i++) {
                int finalI = i + 1;
                pool.execute(() -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "=>" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        Thread.sleep(5000);
    }
}
