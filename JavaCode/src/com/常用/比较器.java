package com.常用;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparable接口,Comparator接口
 *
 * @author `pjliu`
 * @date 2020/10/10
 */
public class 比较器 {
}

/**
 * 、包装类重写compareTo()方法以后，进行了从小到大的排列
 * 3. 重写compareTo(obj)的规则：
 * 如果当前对象this大于形参对象obj，则返回正整数，
 * 如果当前对象this小于形参对象obj，则返回负整数，
 * 如果当前对象this等于形参对象obj，则返回零。
 */

class ComparableTest {
    public static void main(String[] args) {
        Goods[] goods = new Goods[3];
        for (int i = 0; i < 3; i++) {
            goods[i] = new Goods("刘鹏杰" + String.valueOf(i), 10 - i);
        }
        Arrays.sort(goods);
        for (Goods good :
                goods) {
            System.out.println(good.toString());
        }
    }
}

/**
 * 当元素的类型没实现java.lang.Comparable接口而又不方便修改代码，
 * 或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，
 * 可以考虑使用 Comparator 的对象来排序
 */
class ComparatorTest {
    public static void main(String[] args) {
        //将Goods按照名称排序，Goods的CompareTo没有实现
        Goods[] goods = new Goods[3];
        for (int i = 0; i < 3; i++) {
            goods[i] = new Goods("刘鹏杰" + String.valueOf(9 - i), 10 - i);
        }
        //Comparator.comparing()方法可以减少逻辑。
        Arrays.sort(goods, Comparator.nullsFirst(Comparator.comparing(Goods::getPrice).thenComparing(Goods::getPrice)));
        for (Goods good :
                goods) {
            System.out.println(good.toString());
        }
    }
}

class Goods implements Comparable<Goods> {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Goods anotherGoods) {
        return Double.compare(this.price, anotherGoods.price);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
