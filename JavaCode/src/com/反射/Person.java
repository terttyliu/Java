package com.反射;

/**
 * @author pjliu
 */
public class Person<T> extends Animal<T>{
    static final int anInt=11939;
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

    public void show(){
        System.out.println("I'm a good man");
    }
    private String showNation(String nation){
        System.out.println("我的国籍是"+nation);
        return nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
