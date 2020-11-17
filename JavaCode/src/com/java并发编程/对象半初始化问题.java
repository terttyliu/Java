package com.java并发编程;

public class 对象半初始化问题 {
    private static volatile 对象半初始化问题 instance = null;

    private 对象半初始化问题() {
    }


    public static 对象半初始化问题 getInstance() {
        if (instance == null) {
            /*
    10 monitorenter
    11 getstatic #2 <com/java并发编程/对象半初始化问题.instance>
    14 ifnonnull 27 (+13)
    17 new #3 <com/java并发编程/对象半初始化问题>   ===》为对象分配内存
    20 dup
    21 invokespecial #4 <com/java并发编程/对象半初始化问题.<init>>  ==》执行对象的构造方法，属性初始化
    24 putstatic #2 <com/java并发编程/对象半初始化问题.instance>    ==》将对象地址赋给 instance
    27 aload_0
    28 monitorexit

    如果不对instance对象使用 volatile 关键字， 24和21行指令可能会发生重排序（在单线程下没有错误），造成其他线程错误访问到还没有执行构造方法的内存区域。
             */

            synchronized (对象半初始化问题.class) {
                if (instance == null) {
                    instance = new 对象半初始化问题();
                }
            }
        }
        return instance;
    }
}
