package com.java基础.常用;

import java.lang.annotation.Annotation;

/**
 * jdk8新增特性
 * 可重复注解、类型注解
 * @author `pjliu`
 * @date 2020/10/12
 */
/*
可重复注解：
    jdk8之前：
@MyAnnotations({@MyAnnotation("Hello"),@MyAnnotation("world")})
    jdk8之后:
 */
@MyAnnotation("Hello")
@MyAnnotation("world")
/*
类型注解：
ElementType.TYPE_PARAMETER
ElementType.TYPE_
 */
public class 注解 {
    public static void main(String[] args) {
        //通过反射获取注解
        Class<AnnotationTest> aClass = AnnotationTest.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation a :
                annotations) {
            System.out.println(a);
        }

    }
}

@MyAnnotation()
class AnnotationTest {
}

