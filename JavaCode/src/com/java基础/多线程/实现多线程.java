package com.java基础.多线程;

import java.util.concurrent.*;

/**
 * 有四种方式实现多线程
 * 1. 继承 Thread
 * 2. Runnable
 * 3. Callable
 * 4. 线程池
 * <p>
 * <p>
 * Runnable 的优势：
 * 1 不局限于单继承
 * 2 实现了安全的数据共享
 * 3
 *
 * @author `pjliu`
 * @date 2020/10/07
 */
public class 实现多线程 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Future接口表示一个任务的生命周期
        // Future提供了三种功能：
        //
        //判断任务是否完成；
        //中断任务；
        //获取任务执行结果。
        //
        //Runnable和Callable描述的都是抽象的计算任务。
        //Future只是一个接口，无法直接创建对象，因此有了FutureTask。
//        FutureTask<String> task = new FutureTask<>(new MyThread_3(10));
        //start()执行FutureTask中重写的Run()方法,而Run()方法中调用了Callable实现类的Call():V
//        new Thread(task).start();

        Thread thread = new Thread(() -> {
            try {
                System.out.println("我要睡觉");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("别打扰我睡觉");
                e.printStackTrace();
            }
            System.out.println("休眠结束");
        });
        thread.start();
        Thread.sleep(1000);
        if (!thread.isInterrupted()) {
            thread.interrupt();
            Thread.sleep(2000);
            System.out.println("线程中断");
        }
//        new Thread(new FutureTask<Void>(() -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(Thread.currentThread().getName() + ":" + i);
//            }
//            return null;
//        })).start();
        //FutureTask中，Run()将结果使用Set()保存，可以通过Future get()
    }

    private static void run() {

    }
}


class MyThread_1 extends Thread {


    int ticket = 5;

    @Override
    public void run() {
        System.out.println("MyThread extends Thread");
        while (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + " " + ticket--);
        }
    }

}

class MyThread_2 implements Runnable {


    int ticket = 5;


    public MyThread_2(int ticket) {
        this.ticket = ticket;
    }


    public MyThread_2() {
    }


    @Override
    public void run() {
        System.out.println("MyThread_2 implements Runnable");
        while (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + " " + ticket--);
        }
    }
}

class MyThread_3 implements Callable<String> {
    int ticket;

    public MyThread_3(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public String call() throws Exception {
        System.out.println("执行Callable");
        while (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "卖票:" + ticket--);
        }
        return "票卖完了";
    }
}

class MyThread_4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        MyThread_3 thread_3 = new MyThread_3(10);
        Future<String> submit = service.submit(thread_3);
        System.out.println(submit.get());
    }
}