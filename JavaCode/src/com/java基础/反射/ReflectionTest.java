package com.java基础.反射;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author pjliu
 */
public class ReflectionTest {
    /**
     * 反射之前，在Person类外部，不可以通过类的对象调用其内部私有结构。
     * 如Person的name，showNation（） 以及私有的构造器
     */
    @Test
    public void testReflection() throws Exception {
        Class<Person> clazz = Person.class;
        //1 通过反射，创建Person类的对象。
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object tom = cons.newInstance("Tom", 12);
        Person p = (Person) tom;
        System.out.println(p.toString());

        //2 通过反射，调用对象指定的属性、方法
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());
        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //通过反射，调用类的私有结构。如私有构造器，属性，方法。
        //私有构造器
        Constructor<Person> cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person jeff = cons1.newInstance("Jeff");
        System.out.println(jeff.toString());
        //私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        Object strName = name.get(p);
        System.out.println(strName);
        name.set(p, "刘鹏杰");
        System.out.println(p);
        //私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p, "中国");
        System.out.println(nation);
    }

    /**
     * 获取Class实例的方式
     */
    @Test
    public void testGetClassObject() throws ClassNotFoundException {
        //方式一 调用运行时类的属性
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        //方式二 通过运行时类的对象
        Person p = new Person();
        Class<? extends Person> clazz2 = p.getClass();

        //方式三 调用Class的静态方法 forName(String classPath)
        Class<?> clazz3 = Class.forName("com.java基础.反射.Person");
        System.out.println(clazz3);

        //方式四 类的加载器 ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.java基础.反射.Person");
        System.out.println(clazz4);

        //加载到内存中的运行时类，会缓存一定时间。
        // 在此时间之内，我们可以通过不同的方式来获取此运行时类。
        System.out.println(clazz1 == clazz2 && clazz2 == clazz3);
        System.out.println(clazz3 == clazz4);
    }

    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        Class<Person> clazz = Person.class;
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        Object o = new Person("鹏杰", 25);
        name.set(o, "刘鹏杰");
        Object o1 = name.get(o);
        System.out.println(o1);
        //访问静态域
        Field anInt = clazz.getDeclaredField("anInt");
        anInt.setAccessible(true);
        Object o2 = anInt.get(clazz);
        System.out.println(o2);
        anInt.set(clazz, 1997);
        Object o3 = anInt.get(clazz);
        System.out.println(o3);
    }

    @Test
    public void testMethod() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<Person> clazz = Person.class;
        Method method = clazz.getDeclaredMethod("showNation", String.class);
        //保证当前属性是可访问的
        method.setAccessible(true);
        Object o = new Person("鹏杰", 25);
        Object o1 = method.invoke(o, "中国");
        System.out.println(o1);

    }

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Person> clazz = Person.class;
        Person person = clazz.getDeclaredConstructor(String.class, int.class).newInstance("刘鹏杰", 23);
        System.out.println(person);
    }
}
