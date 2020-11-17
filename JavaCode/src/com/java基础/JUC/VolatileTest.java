package com.java基础.JUC;


import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    /*
    此时会导致程序无法结束，这是因为内存可见性的问题。
    内存可见性：多个线程操作共享数据时，彼此不可见
    volatile：当多个线程操作共享数据时，可以保证内存中的数据是可见的。
    注意：
    1 volatile 不能保证数据的"互斥性"
    2 volatile 不能保证数据的"原子性"
     */
    @Test
    public void testVolatile() {

        class MyThread implements Runnable {
            private volatile boolean flag = false;

            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
                System.out.println("flag=" + isFlag());
            }

            public boolean isFlag() {
                return flag;
            }

        }
        MyThread mt = new MyThread();
        new Thread(mt).start();
        while (true) {
            if (mt.isFlag()) {
                System.out.println("----------------");
                break;
            }
        }

    }


    /*
    测试数据的原子性；
    一 i++的原子性:i++相当于三个步骤
        int i=10;
        i=i++;
        相当于
        int temp=i;
        i=i+1;
        i=temp;

    volatile只能保证数据的可见性（操作数据时，相当于直接在主存中操作。）但不能保证多线程间指令的顺序执行（互斥性）。
     */
    @Test
    public void testVolatileAtomic() throws InterruptedException {
        class MT implements Runnable {
            public int getSerialNum() {
                return serialNum++;
            }

            private volatile int serialNum = 1;

            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + getSerialNum());
            }
        }
        MT mt = new MT();
        for (int i = 0; i < 10; i++) {
            new Thread(mt).start();
        }
        Thread.sleep(2000);
    }

    /*
    二 原子变量juc.atomic提供了常用的原子变量
        1.volatile 保证可见性
        2.CAS 操作保证数据的原子性
        CAS：
        当且仅当内存中的值和保存的旧值相等时，将计算后的结果赋给原值。

        CAS的缺点：ABA问题（变量被改后又变回去了）；长时间阻塞开销大；只能保证单一变量的原子性
     */
    @Test
    public void testVolatileAtomic2() throws InterruptedException {
        class MT implements Runnable {
            public int getSerialNum() {
                return serialNum.getAndIncrement();
            }

            private volatile AtomicInteger serialNum = new AtomicInteger(1);

            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + getSerialNum());
            }
        }
        MT mt = new MT();
        for (int i = 0; i < 10; i++) {
            new Thread(mt).start();
        }
        Thread.sleep(2000);
    }
}

