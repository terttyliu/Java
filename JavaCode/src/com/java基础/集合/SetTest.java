package com.java基础.集合;


import com.java基础.Chapter29.Person;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *     |----Set接口：存储无序的、不可重复的数据   -->高中讲的“com.java基础.集合”。
 *           |----HashSet         Set的主要实现类；线程不安全；可以存储null。
 *                |----LinkedHashSet HashSet的子类，遍历内部数据时，能按照添加时顺序遍历
 *           |----TreeSet         底层使用红黑树；可以按照对象的某一属性排序；要求数据为同一类new的，需要实现Comparable

 * 1.无序性
 * 在存储时并非按照索引顺序进行添加。（按照某种算法）
 * 2.不可重复性
 * HashSet按照hashCode()和equals() 进行判断。TreeSet采用Comparable进行判断。
 * 按照hashcode和计算公式，存储在数组中。
 * 若此位置已经有元素b，但是hash值不同。则构成单链表a->b
 * 若hash值相同但equals返回false，添加成功。
 * 若hash值相同但equals返回true，添加失败。
 * 数组初始值为16，当使用率超过0.75，则容量扩大为原来的2倍。
 *
 * @author `pjliu`
 * @date 2020/10/13
 */
public class
SetTest {
    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add(111);
        hashSet.add("AAA");
        hashSet.add("AAA");
        hashSet.add(new Person("刘鹏杰", 22));
        //未重写equals时比较的是地址值
        hashSet.add(new Person("刘鹏杰", 22));
        hashSet.add(new Date());
        hashSet.add(456);
        for (Object o :
                hashSet) {
            System.out.println(o);
        }

//        TreeSet只能添加相同类的对象(该类要实现)Comparable
//        TreeSet treeSet = new T reeSet();
//        treeSet.add("aaa");
//        treeSet.add(123);
        TreeSet<Person> treeSet = new TreeSet<>(Comparator.comparing(Person::getName));
        System.out.println(treeSet.comparator());
        treeSet.add(new Person("pjliu",22));
        treeSet.add(new Person("pjliu",23));
        treeSet.add(new Person("pjli",24));
        for (Person p :
                treeSet) {
            System.out.println(p);
        }
    }
}
