package com.反射;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * 获取运行时类的方法结构s
 */
public class GetAllOfClassTest {
    @Test
    public void testField() {
        Class<Person> aClass = Person.class;

        //得到该类的所有属性
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f : declaredFields) {
            int modifiers = f.getModifiers();
            Class<?> type = f.getType();
            String name = f.getName();
            System.out.printf("%s \t %s \t %s\n",Modifier.toString(modifiers),type,name);
        }
        System.out.println("********************************");
        //得到该类及其父类的所有public属性
        Field[] fields = aClass.getFields();
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            Class<?> type = f.getType();
            String name = f.getName();
            System.out.printf("%s \t %s \t %s\n",Modifier.toString(modifiers),type,name);
        }
    }

    /**
     * 获取运行时类的方法结构
     */
    @Test
    public void testMethod() {
        //得到该类及其父类的所有public方法
        Class<Person> clazz = Person.class;
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println("**********************");
        //得到该类的所有方法（不包含父类）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }

    /**
     * 获取其他结构
     * 1 构造器 getConstructors() 拿不到父类构造器
     * 2 运行时类的父类/带泛型父类/
     * 3 运行时类实现的接口
     * 4 运行时类所在的包
     * 5 运行时类声明的注解
     */
    @Test
    public void testOther() {
        Class<Person> aClass = Person.class;
        Class<? super Person> superclass = aClass.getSuperclass();
        Type superclass1 = aClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass1;
        System.out.println(parameterizedType.getActualTypeArguments()[0]);
    }
}
