package com.java基础.Chapter29;

import java.io.Serializable;
import java.util.Objects;

/**
 * 对象想要实现序列化，需要实现下列接口之一
 * 1.1 Serializable   (标识接口，没有需要实现的方法)
 * 1.2 Externalizable
 * 2 需要提供全局常量 serialVersionUID
 * 3 Person类的属性也得是可序列化的（基本数据类型都是可序列化的）
 * 4 被 static 和 transient 修饰的成员变量不能序列化
 * @author pjliu
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 234343243219998L;
    private final String name;
    private final int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
