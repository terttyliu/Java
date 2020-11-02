package com.java基础.多线程;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *
 *
 * * @author `pjliu`
 * @date 2020/10/08
 */
public class
常用操作 {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo info : threadInfos) {
            System.out.println(info.getThreadId()+"  "+info.getThreadName());
        }
    }
}


/**
 * 线程强制执行
 * o.join():void
 * t.join()方法阻塞调用此方法的线程(calling thread)进入 TIMED_WAITING 状态，直到线程t完成，此线程再继续；
 */
class ForceRun {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int x = 0; x < 100; x++) {
                System.out.println("[霸道线程]：x=" + x);
            }
        });
        thread.start();
        thread.resume();
        for (int x = 0; x < 100; x++) {
            if (x > 3) {
                //
                thread.join();
            }
            System.out.println("[普通线程]：x=" + x);
        }
    }

}

/**
 * Thread.yield()
 */
class Thread礼让 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int x = 0; x < 100; x++) {
                System.out.println("[霸道线程]：x=" + x);
            }
        });
        thread.start();
        for (int x = 0; x < 100; x++) {
            if (x % 3 == 0) {
                //
                Thread.yield();
                System.out.println("普通线程进行礼让********");
            }
            System.out.println("[普通线程]：x=" + x);
        }
    }
}

/**
 * o.getPriority(int)
 * Thread.MAX_PRIORITY
 * Thread.MIN_PRIORITY
 */
class Thread优先级{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int x = 0; x < 100; x++) {
                System.out.println("[霸道线程]：x=" + x);
            }
        });
        thread.start();
        for (int x = 0; x < 100; x++) {
            if (x % 3 == 0) {
                //
                Thread.yield();
                System.out.println("普通线程进行礼让********");
            }
            System.out.println("[普通线程]：x=" + x);
        }

        System.out.println(Thread.currentThread().getPriority());;
    }
}