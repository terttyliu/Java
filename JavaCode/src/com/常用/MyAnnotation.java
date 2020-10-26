package com.常用;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 以下四个注解都是元注解（修饰注解的注解）
 * @author pjliu
 */
//生命周期
@Retention(RetentionPolicy.RUNTIME)
//标明该注解能标注那些内容
@Target(value = {TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
//标明该注解在javadoc解析时会保留(比如@Deprecated)
@Documented
///用于表示某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
@Inherited

//可重复注解：jdk8之后
//注意，若想这样使用，MyAnnotations的生命周期要大于等于MyAnnotation。
//MyAnnotations的Target要是MyAnnotation的子集
//最简单的就是Target和Retention相同
 @Repeatable(MyAnnotations.class)

public @interface MyAnnotation {
    String value() default "hello";
}
