package com.反射;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.SplittableRandom;

/**
 * 通过反射创建对应的运行时类的对象
 * <p>
 * 可能出现的异常 ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
 * <p>
 * IllegalAccessException 非法访问（权限不够）
 * InstantiationException 必须提供空参构造器
 * <p>
 * <p>
 * JavaBean为什么要求提供一个public的空参构造器
 * 1 便于通过反射创建运行时类的对象
 * 2 继承时会默认调用父类的空参构造器
 *
 * @author pjliu
 */
public class NewInstanceTest {
    @Test
    public void testNewInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName("com.反射.Person");
        //方式一
        System.out.println(clazz.newInstance());

        //方式二
        //相较于clazz.newInstance()  可以调用含参构造函数
        Object obj = clazz.getDeclaredConstructor(String.class, int.class).newInstance("刘鹏杰", 22);
        System.out.println(obj);
    }

    /**
     * 体现反射的动态性
     */
    @Test
    public void test2() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        //0,1,2
        int num = new Random().nextInt(3);
        String classPath = null;
        switch (num) {
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "com.反射.Person";
                break;
            default:
                break;
        }
        Object instance = getInstance(classPath);
        System.out.println(instance);
    }

    public Object getInstance(String classPath) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
