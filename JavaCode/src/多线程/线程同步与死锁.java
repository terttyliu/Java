package 多线程;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步可以使用 synchronized 和 ReentrantLock 实现.
 * 面试题： 二者区别：
 * lock 需要手动释放，synchronized
 * @author `pjliu`
 * @date 2020/10/08
 */
public class 线程同步与死锁 {
    public static void main(String[] args) {
        Thread同步 myThread = new Thread同步(1000);
        new Thread(myThread, "窗口1").start();
        new Thread(myThread, "窗口2").start();
        new Thread(myThread, "窗口3").start();
    }
}

/**
 * synchronized(监视器)
 */
class Thread同步 implements Runnable {

    int ticket;

    public Thread同步(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖票：" + ticket--);
                } else {
                    break;
                }
            }
        }
        System.out.println("没票了");
    }
}

/**
 * ReentrantLock
 */
class Thread同步2 implements Runnable {
    private int ticket = 300;
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        //测试可重复上锁。
        //method1();
        while (true){
            lock.lock();
            try {
                if (ticket>0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":ticket="+ticket--);
                }else {
                    break;
                }
            }finally {
              //  lock.unlock();
            }
        }
    }
    private void method1() {
        lock.lock();
        try {
            System.out.println("lock");
            method2();
        } finally {
            lock.unlock();
        }
    }
    private void method2() {
        lock.lock();
        try {
            System.out.println("再次lock");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread同步2 同步2 = new Thread同步2();
        new Thread(同步2).start();
        new Thread(同步2).start();
        new Thread(同步2).start();
    }
}

/**
 * 死锁：若干个线程互相等待的状态。
 */
class 死锁 implements Runnable {

    private A a = new A();
    private B b = new B();


    @Override
    public void run() {
        a.say(b);
    }

    public 死锁() {
        new Thread(this).start();
        b.say(a);
    }

    public static void main(String[] args) {
        new 死锁();
    }
}

class A {


    public synchronized void say(B b) {
        System.out.println(Thread.currentThread().getName() + "获得A的对象锁");
        b.get();
    }

    public synchronized void get() {
        System.out.println(Thread.currentThread().getName() + "获取A的对象锁");
    }

}

class B {

    public synchronized void say(A a) {
        System.out.println(Thread.currentThread().getName() + "获得B的对象锁");
        a.get();
    }

    public synchronized void get() {
        System.out.println(Thread.currentThread().getName() + "获取B的对象锁");
    }

}