package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC中的锁测试
 */
public class LockTest {
    @Test
    public void testSynchronized() {
        Ticket ticket = new Ticket();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    ticket.sale();
                }
            }).start();
        }

    }

    class Ticket {
        private int ticket = 100;
        private Lock lock = new ReentrantLock();

        public void sale() {
            lock.lock();
            try {
                System.out.println(ticket--);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
