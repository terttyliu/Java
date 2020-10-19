package 集合;


import javaLearn2.Chapter29.Person;

import java.util.HashMap;

/**
 *
 * Collections可以操作Set List Map
 *
  * 1.Collection和Collections的区别
 *
 * @author `pjliu`
 * @date 2020/10/17
 */
public class CollectionsTest {
    public static void main(String[] args) {
        HashMap<Person, Integer> map = new HashMap<>();
        map.put(new Person("蒲柳",12),12);
        map.put(new Person("花",13),12);
        map.put(new Person("草",14),12);
        System.out.println(map);
    }
}
