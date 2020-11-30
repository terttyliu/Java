package com.JVM.垃圾回收器和内存分配策略;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.ref.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 四种引用的测试
 *
 * @author pjliu
 */
public class 四种引用 {
    private static int _1MB = 1024 * 1024;

    @Test
    public void testStrongRef() throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.out.println(m);
        System.in.read();
    }

    @Test
    public void testSoftRef() {
        SoftReference<byte[]> ref = new SoftReference<>(new byte[10 * _1MB]);
        System.out.println(ref.get());
        System.gc();
        System.out.println(ref.get());
        SoftReference<byte[]> ref2 = new SoftReference<>(new byte[15 * _1MB]);
        System.out.println(ref.get());
        System.out.println(ref2.get());
    }

    @Test
    public void testWeakRef() throws InterruptedException {
        WeakReference<byte[]> ref = new WeakReference<>(new byte[10 * _1MB]);
        System.out.println(ref.get());
        System.gc();
        Thread.sleep(500);
        System.out.println(ref.get());
    }

    private static List list = new LinkedList<>();
    private static ReferenceQueue<M> queue = new ReferenceQueue<>();

    public static void main(String[] args) {
        new 四种引用().testPhantomRef();
    }

    /*
    虚引用可以用在直接内存回收。
     */
    public void testPhantomRef() {
        PhantomReference<M> ref = new PhantomReference<>(new M(), queue);
        System.out.println(ref.get());
        new Thread(() -> {
            int count = 0;
            while (true) {
                list.add(new byte[_1MB]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(count++ + " " + ref.get());
            }
        }).start();
        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = queue.poll();
                if (poll != null) {
                    System.out.println("jvm回收了虚引用所指向的对象" + poll);
                }
            }
        }).start();
    }
}

class M {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}