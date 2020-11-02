package com.java基础.IO流;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class 统计字符出现次数 {
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            File file = new File("Hello.txt");
            fr = new FileReader(file);
            HashMap<Character, Integer> map = new HashMap<>();
            int data = -1;
            while ((data = fr.read()) != -1) {
                char ch=(char)data;
                if (!map.containsKey(ch)) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }
            for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
                System.out.println(characterIntegerEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if
            (fr!=null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
