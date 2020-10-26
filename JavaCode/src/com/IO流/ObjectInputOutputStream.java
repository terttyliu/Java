package com.IO流;

import com.Chapter29.Person;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 4 对象流 ObjectInputStream ObjectOutputStream
 * 将java中的对象写入到数据源或从数据源中读取
 * <p>
 * 序列化: 将内存中的Java对象转换成平台无关的二进制流。从而持久的保存在磁盘或通过网络传输
 * 其他程序获得了该二进制流，就可以恢复源对象。
 *
 * @author pjliu
 */
public class ObjectInputOutputStream {
    /**
     * 将内存中的Java对象序列化 想要序列化，需要满足一定要求，详见Person类
     */
    @Test
    public void testObjectOutputStream() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("Object.data"));
            oos.writeObject(new Person("阿斗",23));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 反序列化
     */
    @Test
    public void testObjectInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("Object.data"));
            Object obj = null;
            try {
                obj = (Person) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
