package 集合;


import javaLearn2.Chapter29.Person;

import java.util.*;

/**
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *           |----List接口：存储序的、可重复的数据。  -->“动态”数组
 *              |----ArrayList、LinkedList、Vector
 *
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet、LinkedHashSet、TreeSet
 *
 * @author `pjliu`
 * @date 2020/10/12
 */
public class 集合 {

}

/**
 * Collection 方法使用
 */
class CollectionTest {
    public static void main(String[] args) throws InterruptedException {
        Collection col1 = new ArrayList();

        //add(<E> e):boolean
        col1.add("AA");
        col1.add(123);
        col1.add(new Date());

        //size():int
        System.out.println(col1.size());

        //addAll():boolean
        Collection col2 = new ArrayList();
        col2.add(12.111);
        col2.add(12.333);
        col1.addAll(col2);
        System.out.println(col1);

        //isEmpty():boolean
        System.out.println(col1.isEmpty());

        //contains(<E> e):判断集合是否包含e
        //注意，该方法效率为O(n )
        System.out.println(col1.contains(12.111));
        Person person = new Person("刘鹏杰", 22);
        col1.add(person);
        //contains()调用的是equals()
        System.out.println(col1.contains(new Person("刘鹏杰", 22)));

        //remove(<E> E) 只是将e从集合中移除，并没有释放内存。
        col1.remove(person);
        System.out.println(col1.contains(new Person("刘鹏杰", 22)));
        System.out.println(person);

        //removeAll(Collection col) 集合中的差集
        col1.removeAll(col2);

        //retainAll(Collection col) 集合中的交集
        col1.retainAll(col2);

        //equals() 比较所有元素
        col1.equals(col2);

        //获取hashcode
        System.out.println(col1.hashCode());

        //toArray()
        Object[] array = col1.toArray();
        for (Object a :
                array) {
            System.out.println(a);
        }

        //Arrays.asList(T....t)
        //注意：Arrays.asList(new int[]{1,2})会被识别为一个元素，这个元素是一个以为数组。
        System.out.println(Arrays.asList(new int[]{1, 2}));//[[I@9629756]
        System.out.println(Arrays.asList(new Integer[]{1, 2}));//[1, 2]
        System.out.println(Arrays.asList(new String[]{"1", "2"}));//[1, 2]

        //iterator():返回Iterator接口的实例，用于遍历集合元素
        // 每次调用都会获得一个全新的迭代器。
        //Iterator本身不是容器，只是一个用于遍历容器的对象。
        System.out.println("****************");
        col1.add("AA");
        col1.add(null);
        col1.add(9);
        col1.add(new Date());
        Iterator iterator = col1.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        //NoSuchElementException
        //System.out.println(iterator.next());
        //Iterator开始时，指针指向容器第一个容器之前。也就是说，第一次执行iterator.next()时，指针才指向第一个元素
        System.out.println("*********");
        iterator = col1.iterator();
        for (Object o :
                col1) {
            System.out.println(o);
            if (Integer.valueOf(9).equals(o)) {
                o=Integer.valueOf(10);//错误写法 容器里面 第二位-->Integer(9)@912 object-->第二位() object-->
                //这个o不是col1[2]本身，而是指向了col1[2]指向的元素.
            }
        }
        for (Object o :
                col1) {
            System.out.println(o);
        }

    }
}
