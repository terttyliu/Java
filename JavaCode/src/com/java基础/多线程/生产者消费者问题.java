package com.java基础.多线程;

/**
 * 原始方式：所有同步唤醒都由开发者使用原生代码开发
 * @author `pjliu`
 * @date 2020/10/08
 */
public class 生产者消费者问题 {

    public static void main(String[] args) {

        Message msg = new Message();
        new Thread(new Producer(msg)).start();
        new Thread(new Consumer(msg)).start();
    }

}

class Consumer implements Runnable {

    private Message msg;

    public Consumer(Message msg) {

        this.msg = msg;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(msg.get());
        }
    }

}

class Producer implements Runnable {

    private Message msg;

    public Producer(Message msg) {

        this.msg = msg;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 2 == 0) {
                this.msg.set("刘鹏杰", "帅哥");

            } else {
                this.msg.set("马妍", "美女");
            }
        }
    }

}

/**
 * 因为所有的数据都在Message类中。所以同步可以直接在Message类中实现。
 *
 */
class Message {

    private String title;
    private String content;
    private int MAX_TITLE = 20;
    private int numTile = 0;
    //true  可以生产
    //false 可以消费
    private boolean flag = true  ;

    public synchronized void set(String title, String content) {
        if (!flag) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        this.content = content;
        numTile++;
        flag = false;
        notify();
    }

    public synchronized String get() {
        if (flag) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numTile--;
        flag = true;
        notify();
        return "Message{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}