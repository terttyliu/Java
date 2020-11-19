package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        MyCall myCall = new MyCall();
        FutureTask<String> task = new FutureTask<>(myCall);
        //FutureTask只会调用一次callable.run
        new Thread(task,"0").start();
        new Thread(task,"1").start();
        System.out.println(task.get());

        MR mr = new MR();
        new Thread(mr).start();
        new Thread(mr).start();


        Thread.sleep(200);
    }
    class MR implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "=>" + "执行call");
        }
    }
    class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "=>" + "执行call");
            return "Good";
        }
    }
}
