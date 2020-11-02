package com.java基础.Chapter29;

/**
 * 方法引用
 * <p>
 * 静态方法：       类名称::static 方法名称;
 * 实例对象的方法    实例化对象 ::普通方法；
 * 特定类型的方法    特定类:: 普通方法;
 * 构造方法         类名称 :: new;
 * <p>
 * 利用方法引用可以通过函数时接口，为多个方法定义多个名字。
 * <p>
 * 提供方法引用的概念更多情况下也只是弥补了对于引用的支持功能。
 *
 * @author `pjliu`
 * @date 2020/10/03
 */
public class 方法引用 {
    public static void main(String[] args) {
        //1.静态方法 String.valueOf
        Ifunction<Integer, String> fun = String::valueOf;
        System.out.println(fun.change(100).length());
        //2.实例对象的方法
        Ifunction2<String> fun2 = "pjliu"::toUpperCase;
        System.out.println(fun2.upper());
        //3.不想实例化对象，但是想要使用普通方法。则可是使用特定类来引用方法
        Ifunction3<String> fun3 = String::compareTo;
        System.out.println(fun3.compare("A", "a"));
        //4.构造方法的引用
        Ifunction4<Person> fun4 = Person::new;
        System.out.println(fun4.create("刘鹏杰", 111).toString());
    }


}

@FunctionalInterface
interface Ifunction<P, R> {
    public R change(P p);
}

@FunctionalInterface
interface Ifunction2<R> {
    public R upper();
}

@FunctionalInterface
interface Ifunction3<P> {
    public int compare(P p1, P p2);
}

@FunctionalInterface
interface Ifunction4<R> {
    public R create(String s1, int i2);
}