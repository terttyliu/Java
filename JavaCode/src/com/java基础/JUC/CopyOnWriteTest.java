package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.*;
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

    }
}

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
            //ConcurrentModificationException
            list.add("DD");
        }
    }
}
class MT2 implements Runnable {
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
            //ConcurrentModificationException
            list.add("DD");
        }
    }
}