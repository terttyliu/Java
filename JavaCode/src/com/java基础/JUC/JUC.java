package com.java基础.JUC;

import java.util.concurrent.TimeUnit;

public class JUC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        TimeUnit.SECONDS.sleep(2);
    }
}
