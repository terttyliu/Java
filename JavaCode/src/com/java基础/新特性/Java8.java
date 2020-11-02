package com.java基础.新特性;

import com.java基础.反射.Person;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 8 提供的新特性
 *
 * @author pjliu
 */
public class Java8 {
    /**
     * lambda表达式的本质：作为函数式接口的实例。
     */
    @Test
    public void testLambda() {
        Runnable runnable = () -> {
            System.out.println("***********");
            System.out.println(Thread.currentThread().getName());
        };
        Thread thread = new Thread(runnable);
        thread.start();
        //方法引用
        Comparator<Integer> com = Integer::compare;
        int compare = com.compare(32, 31);
        System.out.println(compare);
    }

    /**
     * java内置的4大核心函数式接口
     * 1 消费型接口 Consumer<T>  void accept(T t)
     * 2 供给型接口 Supplier<T>  T get()
     * 3 函数式接口 Function<T,R> R apply(T t)
     * 4 断言式接口 Predicate<T> boolean test(T t)
     */
    @Test
    public void testFunctionalInterface() {
        Consumer<Double> consumer = (o1) -> System.out.println(o1);
        consumer.accept(21321.21);
    }

    /**
     * StreamAPI 可以对集合数据进行操作，类似于SQL执行的数据库查询。
     * StreamAPi提供了一种高效且易于使用的处理数据的方式。
     * <p>
     * 为什么需要StreamAPI：
     * 多数数据源来自于MySql，Oracle。在SQL层面就可以处理
     * 但现在有了更多的数据源，NoSql需要Java层面进行处理。
     * <p>
     * Stream和Collection的区别：
     * Collection是一种静态的内存数据结构，而Stream是有关计算的。
     * 前者主要面向内存，后者主要面向CPU。集合讲的是数据，Stream讲的是计算。
     * <p>
     * 注意：
     * 1 Stream 不存储元素（类似Iterator）
     * 2 Stream 不改变源对象，他会返回一个持有结果的新Stream
     * 3 Stream操作是延迟执行的，只有需要结果时才会去执行。
     * <p>
     * 执行流程：
     * 1 实例化
     * 2 一系列的中间操作（过滤，映射、、、、）
     * 3 终止操作
     * 中间操作链对数据源的数据进行处理，一旦执行终止操作，会执行中间操作链，并产生结构，之后不再被使用。
     */
    @Test
    public void testStreamAPI() {
        List<Person> persons = Person.getPersons();


        //一、实例化
        System.out.println("4种实例化方式*******************");
        {
            //方式一：通过集合
            Stream<Person> stream1 = persons.stream();
            Stream<Person> personStream1 = persons.parallelStream();

            //方式二：通过数组 对于基本数据类型进行了重载，对于自定义类型，使用泛型。
            int[] arrs = new int[]{1, 2, 3, 4, 5};
            IntStream stream = Arrays.stream(arrs);

            //方式三：Stream.of()
            Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

            //方式四：无限流
            //遍历前十个偶数
            //迭代法
            Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

            //生成
            Stream.generate(Math::random).limit(10).forEach(System.out::println);
        }


        //二、中间操作
        System.out.println("中间操作**********************");
        //1 筛选与切片
        System.out.println("筛选与切片");
        {


            Stream<Person> stream = persons.stream();

            //filter() 排除返回值为false的对象
            stream.filter(e -> e.getAge() > 700).forEach(System.out::println);

            //注意 一旦执行终止操作，就不能再次使用stream
            //limit(n) 使元素不超过给定数量
            persons.stream().limit(2).forEach(System.out::println);

            //skip(n) 返回一个扔掉前n个元素的流，若流中元素不足n个，则返回空。
            persons.stream().skip(2).forEach(System.out::println);

            //distinct() 筛选（去重）
            persons.stream().distinct().forEach(System.out::println);
        }
        //2 映射 map和flapMap 和list.add以及list.addAll一样。
        // 前者将另一个stream作为第一个stream的元素，后者将两个Stream合并返回给第一个stream
        System.out.println("映射");
        {
            //map
            List<String> list = Arrays.asList("aa", "bb", "cc");
            list.stream().map(t -> t.toUpperCase()).forEach(System.out::println);
            Stream<Stream<Character>> streamStream = list.stream().map(Java8::String2chars);
            streamStream.forEach(s -> s.forEach(System.out::println));
            //flapMap(Function f)接收一个函数作为参数。
            Stream<Character> characterStream = list.stream().flatMap(Java8::String2chars);
            characterStream.forEach(System.out::println);
        }
        //3 排序
        System.out.println("排序");
        {
            List<String> list = Arrays.asList("ca", "bb", "cc");
            //sorted()
            list.stream().sorted().forEach(System.out::println);
            //sorted(Comparator com)
            list.stream().sorted((o1, o2) -> {
                return -o1.compareTo(o2);
            }).forEach(System.out::println);
        }


        //三、终止操作
        System.out.println("终止操作***************");
        //1 匹配与查找
        System.out.println("匹配与查找**************");
        {
            //allMatch(Predicate p) 是否全部匹配
            System.out.println(persons.stream().allMatch(p -> p.getAge() > 18));
            //anyMatch() 是否存在匹配
            System.out.println(persons.stream().anyMatch(p -> p.getAge() > 1888));
            //noneMatch() 是否全部都不匹配
            System.out.println(persons.stream().noneMatch(p -> p.getAge() > 1888889));
            //findFirst()
            System.out.println(persons.stream().filter(p -> p.getAge() > 18));
            //findAny()
            System.out.println(persons.parallelStream().findAny());
            //count
            System.out.println(persons.stream().count());
            //max(Comparator x)
            System.out.println(persons.stream().map(p -> p.getName()).max(String::compareTo));
            //min(Comparator x)
            System.out.println(persons.stream().map(p -> p.getName()).min(String::compareTo));
            //forEach(Consumer c)
            persons.stream().forEach(p -> {
                System.out.println(p.toString() + " " + getClass());
            });
        }
        //2 规约 (Reduce)
        System.out.println("规约*******************");
        {
            Integer reduce = Stream.iterate(1, t -> t + 1).limit(10).reduce(0, Integer::sum);
            System.out.println(reduce);
            System.out.println(Stream.iterate(1, t -> t + 1).limit(10).reduce(Integer::max));
        }
        //3 收集 （Collect）
        System.out.println("收集***********");
        {
            Set<String> list = persons.stream().map(Person::getName).collect(Collectors.toSet());
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testOptional() {
        /*
        创建方式：
        1 Optional.of(T t) t不能为null
        2 Empty()  存入空
        3 Optional.ofNullable(T t ) return value == null ? empty() : of(value);
         */
        Person<Object> person = new Person<>("pjliu", 23);
        person = null;
        Optional.ofNullable(person);

        

    }

    public static Stream<Character> String2chars(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

}

