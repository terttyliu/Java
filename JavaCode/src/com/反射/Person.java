package com.反射;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author pjliu
 */
public class Person<T> extends Animal<T> {
    static final int anInt = 11939;
    private String name;
    public int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("I'm a good man");
    }

    private String showNation(String nation) {
        System.out.println("我的国籍是" + nation);
        return nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static List<Person> getPersons() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("刘鹏杰1", 232));
        list.add(new Person("刘鹏杰2", 2233));
        list.add(new Person("刘鹏杰3", 253));
        list.add(new Person("刘鹏杰4", 27653));
        list.add(new Person("刘鹏杰5", 2376));
        list.add(new Person("刘鹏杰6", 234));
        list.add(new Person("刘鹏杰7", 1123));
        list.add(new Person("刘鹏杰7", 1123));
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person<?> person = (Person<?>) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
