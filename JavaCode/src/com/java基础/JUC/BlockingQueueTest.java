package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pjliu
 */
public class BlockingQueueTest {
    @Test
    public void test(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        System.out.println("------1----------");
        /*
        方式一：有异常
         */
        System.out.println(queue.add("A"));
        System.out.println(queue.add("B"));
        System.out.println(queue.add("C"));
        // IllegalStateException: Queue full
        // queue.add("D");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        // NoSuchElementException
        // System.out.println(queue.remove());
        //NoSuchElementException
        //System.out.println(queue.element());
        System.out.println("--------2----------");
        /*
        方式二：无异常
         */
        System.out.println(queue.offer("A"));
        System.out.println(queue.offer("C"));
        System.out.println(queue.offer("D")); // false

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll()); // null
        System.out.println(queue.peek()); // null
        System.out.println("---------3--------");
        /*
        方式三：阻塞
         */
        try {
            queue.put("A");
            queue.put("B");
            queue.put("C");
            //阻塞
            //queue.put("D");
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            //阻塞
            //System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------4-----------");
        /*
        方式四：超时等待 在设定时间内通过lock
         */
        try {
            System.out.println(queue.offer("A", 2, TimeUnit.SECONDS));
            System.out.println(queue.offer("B", 2, TimeUnit.SECONDS));
            System.out.println(queue.offer("C", 2, TimeUnit.SECONDS));
            //
            System.out.println(queue.offer("D", 2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
