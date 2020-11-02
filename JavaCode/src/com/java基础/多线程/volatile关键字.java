package com.java基础.多线程;

/**
 * @author `pjliu`
 * @date 2020/10/08
 */


public class volatile关键字 {

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子B").start();
        new Thread(mt, "票贩子C").start();
    }
}


class MyThread implements Runnable {

    private volatile int ticket = 5;

    @Override
    public void run() {
        while (this.ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out
                    .println(Thread.currentThread().getName() + "卖票处理， ticket = " + this.ticket--);
        }
    }
}
