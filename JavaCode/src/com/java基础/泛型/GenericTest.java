package com.java基础.泛型;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Java的泛型是伪泛型，在编译期间，所有泛型信息都会被擦除（类型擦除）
 *
 * @author pjliu
 */
public class GenericTest {
    @Test
    public void testGeneric() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        // list.add("aa"); (java: 不兼容的类型: java.lang.String无法转换为java.lang.Integer)
        Class<? extends Collection> aClass = list.getClass();
        Method addElement = aClass.getDeclaredMethod("add", Object.class);
        List list2 = (List) aClass.newInstance();
        list2.addAll(list);
        addElement.invoke(list, "aaa");
        System.out.println(list2);
        //[12, aaa]
    }

    @Test
    public void testGeneric2() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        List list2 = new ArrayList<Integer>();
        list2.add(2);
        list2.add("2");
        list2.forEach(t -> {
            System.out.println("类型: " + t.getClass().getSimpleName() + "\t toString(): " + t);
        });
    }
}


