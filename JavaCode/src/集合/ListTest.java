package 集合;


import java.util.ArrayList;

/**
 *
 *
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *           |----List接口：存储序的、可重复的数据。  -->“动态”数组 jdk1.2
 *              |----ArrayList  list主要实现类，因为效率高（线程不安全）底层使用Object[] elementData 存储
 *              |----LinkedList 对于频繁的插入删除操作，效率高。 底层使用双向链表
 *              |----Vector     list古老实现类，效率低（线程安全）底层使用Object[] elementData 存储
 *
 *          |----Set接口：存储无序的、不可重复的数据   -->高中讲的“集合”
 *              |----HashSet、LinkedHashSet、TreeSet
 *
 * ArrayList、LinkedList、Vector三者异同：
 * 同：动态，单列，可重复，有序。
 * 异：
 * ArrayList:jdk1.2  在jdk8 jdk11都发生了改变 依旧是创建了1.5倍空间。（通过 ）
 * LinkedList:jdk1.2
 * Vector:jdk1.0
 * @author `pjliu`
 * @date 2020/10/13
 */
public class ListTest {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
    }
}
