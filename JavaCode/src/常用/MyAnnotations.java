package 常用;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 以下四个注解都是元注解（修饰注解的注解）
 */
//生命周期
@Retention(RetentionPolicy.RUNTIME)
//标明该注解能标注那些内容
@Target(value = {TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
//标明该注解在javadoc解析时会保留(比如@Deprecated)
@Documented
///用于表示某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
@Inherited
public @interface MyAnnotations {
    MyAnnotation[] value();
}
