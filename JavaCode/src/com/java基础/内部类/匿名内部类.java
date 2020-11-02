package com.java基础.内部类;

/**
 * 匿名内部类
 * 是一种简化的内部类处理形式，主要是在抽象类和接口的子类上使用
 * 可以利用静态方法做一个内部的匿名内部类实现
 *
 * 与内部类相比匿名内部类只是一个
 * 没有名字的
 * 只能够使用一次的，
 * 并且结构固定的
 * 一个子类。
 * @author `pjliu`
 * @date 2020/10/03
 */
public class 匿名内部类 {
    public static void main(String[] args) {
        MyMessage.getInstance().send("pjliu");
    }
}

interface MyMessage {
    public void send(String str);

    public static MyMessage getInstance() {
        return new MyMessage() {
            @Override
            public void send(String str) {
                System.out.println(str);
            }
        };
    }
}