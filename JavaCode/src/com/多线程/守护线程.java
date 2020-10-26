package com.多线程;

/**
 * 守护线程：setDaemon() isDaemon()
 * 守护进程：
 * 当程序执行完毕了，守护线程也就消失了。
 * （GC线程）
 * @author `pjliu`
 * @date 2020/10/08
 */
public class 守护线程 {
    public static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            long num = 0;
            while (flag) {
                System.out.println(Thread.currentThread().getName() + ":num=" + num++);
            }
        }, "执行线程");
        Thread daemonThread = new Thread(() -> {
            long num = 0;
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":num=" + num++);
            }
        }, "守护线程");
        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();
        Thread.sleep(1000);
        flag = false;
    }
}
