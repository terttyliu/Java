package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    @Test
    public void test() {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }).start();
    }

    class Data {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();//监视器
        Condition condition2 = lock.newCondition();//监视器
        Condition condition3 = lock.newCondition();//监视器
        private int number = 1;

        public void printA() {
            lock.lock();
            try {
                if (number != 1) {
                    condition1.await();
                }
                //业务代码
                System.out.println("A");
                //业务代码失败了怎么办
                number++;
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB() {
            lock.lock();
            try {
                if (number!=2){
                    condition2.await();
                }
                //业务代码
                System.out.println("B");
                number++;
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printC() {
            lock.lock();
            try {
                if (number!=3){
                    condition3.await();
                }
                //业务代码
                System.out.println("C");
                number=1;
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
