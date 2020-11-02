package com.java基础.集合;


import com.java基础.Chapter29.Person;

import java.io.FileInputStream;
import java.util.*;

/**
 * |----Map:双列数据，存储key-value对的数据   ---类似于高中的函数：y = f(x)
 * |----HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
 * |----LinkedHashMap:保证在遍历map元素时，可以照添加的顺序实现遍历。
 * 原因：在原的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 * 对于频繁的遍历操作，此类执行效率高于HashMap。
 * |----TreeMap:保证照添加的key-value对进行排序，实现排序遍历。此时考虑key的自然排序或定制排序  (Key必须实现Comparable或Compa接口 )
 * 底层使用红黑树
 * |----Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
 * |----Properties:常用来处理配置文件。key和value都是String类型
 * <p>
 * <p>
 * HashMap的底层：数组+链表  （jdk7及之前)
 * 数组+链表+红黑树 （jdk 8)
 * <p>
 * 二.存储结构的理解：
 * >Map中的key:无序的、不可重复的，使用Set存储所的key
 * ---> key所在的类要重写equals()和hashCode() （以HashMap为例)
 * >Map中的value:无序的、可重复的，使用Collection存储所的value
 * --->value所在的类要重写equals()
 * > 一个键值对：key-value构成了一个Entry对象。
 * <p>
 * >Map中的entry:无序的、不可重复的，使用Set存储所的entry
 * 三.HashMap底层原理:
 * jdk7
 * HashMap map= new HashMap()创建了一个长度为16的Entry[] tale.
 * map.put(key_1,value_1)
 * 1--->计算key_1 Hashcode，使用散列算法得到存放位置。
 * 2.1--->若位置上为空，添加成功
 * 2.2--->若不为空，比较与链表上各元素的哈希值。
 * 3.1--->若都不同则添加成功
 * 3.2 --->若相同，调用key_1.equals();
 * 4.1--->quails()返回为true。使用value_1替换原内容。
 * 4.2--->quails()返回为false。添加成功。
 * 在不断的添加过程中，会涉及到扩容问题，当超出临界值(且要存放的位置非空)时，扩容。默认的扩容方式：扩容为原来容量的2倍，并将原的数据复制过来。
 * HashMap在jdk8中相较于jdk7在底层实现方面的不同：
 * 1. new HashMap():底层没创建一个长度为16的数组
 * 2. jdk 8底层的数组是：Node[],而非Entry[]
 * 3. 首次调用put()方法时，底层创建长度为16的数组
 * 4. jdk7底层结构只：数组+链表。jdk8中底层结构：数组+链表+红黑树。
 * 4.1 形成链表时，七上八下（jdk7:新的元素指向旧的元素。jdk8：旧的元素指向新的元素）
 * 4.2 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64时，此时此索引位置上的所数据改为使用红黑树存储。
 * <p>
 * 3.CurrentHashMap 与 Hashtable的异同？
 *
 * @author `pjliu`
 * @date 2020/10/15
 */
public class MapTest {
    public static void main(String[] args) throws Exception {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null, 123);
        map.put(null, 124);
        map.put(new Person("pjliu", 23), 23);
        map.put(new Person("pjliu", 24), 24);
        map.put(new Person("pjliu", 25), 25);
        //会覆盖。
        System.out.println(map.get(null));
        System.out.println(map.get(new Person("pjliu", 23)));
        Set<Map.Entry<Object, Object>> entries = map.entrySet();

        System.out.println();
        TreeMap treeMap1 = new TreeMap<Person, Integer>(Comparator.comparing(Person::getAge));
        treeMap1.put(new Person("刘鹏杰", 23), 1);
        treeMap1.put(new Person("风火轮", 24), 1);
        treeMap1.put(new Person("大象", 25), 1);
        treeMap1.put(new Person("水鼓", 26), 1);
        treeMap1.put(new Person("蚂蜂", 27), 1);
        System.out.println(treeMap1);
        //properties
        Properties jdbc = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        jdbc.load(fis);
        System.out.println(jdbc.getProperty("name"));
        System.out.println(jdbc.getProperty("password"));


        //不填写泛型和<Object>的区别
        Map map1 = new HashMap();
        Map<Object, Integer> map2 = new HashMap();
        map1.put("heelo", 1);
        map2.put("heelo", 1);

        //test1(map2);
        MyMapTest.copyAll(new Object());
        System.out.println("*******************************");
        List Objects=new ArrayList();
        List<String> strings = new ArrayList<>();
        Objects=strings;
        strings.add("pjliu");
        Objects.add(123);
        System.out.println(strings);      }
}

class MyMapTest {
    public static <T> void copyAll(T t) {
        System.out.println(t);
        return;
    }
}
