package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import javax.swing.plaf.SliderUI;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class 常用辅助类 {
    /**
     * 允许一个或多个线程等待，直到其他一组线程的操作完成的一个同步辅助类。
     */
    @Test
    public void testCountDownLatch() throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " go out，剩余人数 ");
                cd.countDown();
            }, String.valueOf(i + 1)).start();
        }
        cd.await();
        System.out.println("锁门了");
    }

    /**
     * 循环屏障
     * 循环屏障允许一组线程全部等待对方到达一个公共屏障点（await）。
     * 具体执行顺序：
     * await()之前的命令 ==》执行CyclicBarrier#中的Runnable# ==》await()之后的操作
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    @Test
    public void testCyclicBarrier() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(6,()->{
            System.out.println("召唤神龙！");
        });
        for (int i = 0; i < 7; i++) {
            int finalI = i+1;
            new Thread(() -> {
                System.out.printf("第%d个龙珠到手了\n",finalI);
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("大家集结了");
            }, String.valueOf(i + 1)).start();
        }
        //System.out.println("锁门了");
    }

    /**
     * 信号量
     * 信号量维持一组许可证。
     * acquire()用于申请许可证，若没有许可证则阻塞。
     * release()用于添加许可证，并潜在的释放阻塞方，若没有阻塞方，则信号量增加。
     * 信号量通常用于限制线程数，而不是访问某些（物理或逻辑）资源。
     */
    @Test
    public void testSemaphore() throws InterruptedException {
        /*
        6辆车抢3个车位
         */
        Semaphore sp = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            final int tempI=i+1;
            new Thread(()->{
                try {
                    sp.acquire();
                    System.out.printf("第%d辆车抢到了车位\n",tempI);
                    Thread.sleep(1000);
                    System.out.printf("第%d辆车释放车位\n",tempI);
                    sp.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(5000);
    }
}
