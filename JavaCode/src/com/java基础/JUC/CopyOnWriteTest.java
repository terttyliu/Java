package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/*
使用CopyOnWriteArraylist可以在多线程下在迭代时对数据进行修改。
 */
public class CopyOnWriteTest {
    @Test
    public void testArraylist() {
        MT mt = new MT();
        for (int i = 0; i < 1; i++) {
            new Thread(mt).start();
        }
        ArrayList<String> strings = new ArrayList<>();
    }
    @Test
    public void testCopyOnWriteList(){
        MT2 mt = new MT2();
        for (int i = 0; i < 10; i++) {
            new Thread(mt).start();
        }
        ArrayList<String> strings = new ArrayList<>();
    }
}
//会产生错误
class MT implements Runnable {
    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            //ConcurrentModificationException 错误
            list.add("DD");
        }
    }
}
class MT2 implements Runnable {
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(Thread.currentThread().getName()+":"+it.next());
            //ConcurrentModificationException
            list.add("DD");
        }
    }
}