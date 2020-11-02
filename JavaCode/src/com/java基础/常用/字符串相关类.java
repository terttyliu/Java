package com.java基础.常用;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * String类常用方法,StringBuffer,StringBuilder
 * <p>
 * String特性
 * 1、String 是final类（不可变的字符序列 ），不可继承
 * 2、字符串的值存储在char[] value中（jdk9之后使用byte数组）
 * <p>
 * <p>
 * 方法区和堆都是线程共享的内存区域，方法区用于存储：类信息、普通常量、静态变量、编译器编译后代码。
 * 1.6之前  字符串常量池在方法区，1.7字符串常量池在堆中，1.8之后在元空间。
 *
 * @author `pjliu`
 * @date 2020/10/10
 */
public class 字符串相关类 {
}

/**
 * String实现了几个接口：
 * java.io.Serializable, 支持序列化的。 对象可以进行传输的。对象默认不可以传输。
 * Comparable<String>,   可以比较大小。
 * CharSequence
 * 常用方法：
 * str.intern():String  Returns a canonical representation for the string object.
 */
class StringTest {

    public static void main(String[] args) {
        StringTest test = new StringTest();
        test.test1();
        char a = '缏';
        System.out.println(a);
    }

    /**
     * new String()在堆中创建了对象，其中的value指向了方法区中"abc"的地址。
     */
    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s1 == s2);
    }
}

/**
 * 底层结构和String相同，jdk9前使用char[]，jdk9开始使用byte[]
 * StringBuffer jdk1.0 线程安全的 可变的字符序列 效率低
 * StringBuilder jdk1.5新增 线程不安全的 可变的字符序列 效率高
 * 运行效率：StringBuilder>StringBuffer>>String
 */
class StringBufferBuilderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //StringBuffer和Builder默认数组比输入的String大16个单位
        // (若编码为LATIN1，则使用new byte[int]；若编码为UTF16,则使用StringUTF16.newBytesFor(int))；
        //开发可以使用new StringBuffer(int) 控制初始容量
        StringBuffer sb1 = new StringBuffer("你好");
        StringBuffer sb2 = sb1;
        String s1 = "你好";
        String s2 = s1;
        sb2.append('3');
        s2 += '3';
        byte[] bytes=s1.getBytes();
        byte[] bytes2=s1.getBytes("gbk");
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(bytes2));
    }
}