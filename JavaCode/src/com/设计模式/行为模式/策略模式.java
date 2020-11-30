package com.设计模式.行为模式;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.Arrays;

/**
 * @author pjliu
 */
public class 策略模式 {
    public static void main(String[] args) {
        Cat[] cats = new Cat[]{
                new Cat(1, 1),
                new Cat(54, 1),
                new Cat(3, 1)};
        sort(cats);
        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }

    public static void sort(Comparable[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            int minPos = i;
            for (int j = i + 1; j < arrs.length; j++) {
                minPos = arrs[j].compareTo(arrs[minPos]) == -1 ? j : minPos;
            }
            swap(arrs, i, minPos);
        }
    }

    private static void swap(Comparable[] arrs, int i, int j) {
        Comparable temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }
}

class Cat implements Comparable<Cat> {
    int weight, height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Cat o) {
        return Integer.compare(this.weight, o.weight);
    }
}
