package com.java基础.反射;

import java.io.Serializable;

/**
 * @author pjliu
 */
public class Animal<T> implements Serializable {

    private char gender;
    public double weight;
    private void breath(){
        System.out.println("生物呼吸");
    }
    public void eat(){
        System.out.println("生物吃东西");
    }

}
