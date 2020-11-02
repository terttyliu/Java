# Java新特性

## Java8

便于并行:Stream API

优化空指针异常 Optional 

==Nashorn引擎== 允许在JVM上运行JS应用 (jjs.exe)

![Java 8新特性 尚硅谷-宋红康](https://raw.githubusercontent.com/terttyliu/Java/main/img/20201026130300.bmp)

* 接口
  提供了 ==默认方法==和==静态方法==  

* 常用类
  提供了新的==日期API==：`LocalDateTime` `Instant` `DateTimeFormator` 
*  注解
  类型注解，重复注解
* 集合
   延时数组，HashMap底层添加红黑树。

### 其他新特性

* Lambda表达式
* 函数式接口
* 方法引用和构造器引用
* Stream API
* Optional类 

####  Stream API

StreamAPI 可以对==集合数据进行操作==，类似于SQL执行的数据库查询。 StreamAPI提供了一种高效且易于使用的处理数据的方式。 

* 为什么需要StreamAPI： 
  多数数据源来自于MySql，Oracle。在SQL层面就可以处理 但现在有了更多的数据源，==NoSQL需要Java层面进行处理。==
*  Stream和Collection的区别：
   Collection是一种==静态的内存数据结构==，而Stream是有关计算的。 前者主要面向内存，后者主要面向CPU。集合讲的是数据，Stream讲的是计算。
*  注意：
  1. Stream 不存储元素（类似==Iterator==） 
  2. Stream 不改变源对象，他会返回一个持有结果的新Stream 
  3. Stream操作是==延迟执行==的，只有需要结果时才会去执行。

![image-20201026143106879](https://raw.githubusercontent.com/terttyliu/Java/main/img/Stream%E6%93%8D%E4%BD%9C%E6%AD%A5%E9%AA%A420201026143106.png)

## Java9

