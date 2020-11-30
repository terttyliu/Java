package com.java基础.JUC;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author pjliu
 */
public class StreamTest {
    @Test
    public void test() {
        Collection<User> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new User(i, Character.toString((char) ('A' + i)), i + 20));
        }
        Stream<User> stream = list.stream();
        stream.filter(user -> user.age % 2 == 0).map(user -> {
            user.age++;
            return user;
        }).forEach(System.out::println);
    }

    class User {
        int id;
        String name;
        int age;

        public User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
