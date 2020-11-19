package com.java基础.JUC;

import org.junit.jupiter.api.Test;

public class 生产者消费者 {
    //Synchronized版本
    @Test
    public void test1() throws InterruptedException {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
        Thread.sleep(2000);
    }

    class Data {
        private int number = 0;

        public synchronized void increment() throws InterruptedException {
            while (number != 0) {
                this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ":数量为" + number);
            this.notifyAll();
        }

        public synchronized void decrement() throws InterruptedException {
            while (number == 0) {
                this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ":数量为" + number);
            this.notifyAll();
        }
    }
}
