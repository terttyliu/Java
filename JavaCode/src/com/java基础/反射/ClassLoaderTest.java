package com.java基础.反射;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 *
 * @author pjliu
 */
public class ClassLoaderTest {
    /**
     * 三种不同的类加载器
     */
    @Test
    public void testClassLoader() {
        //系统类加载器 ClassLoaders$AppClassLoader
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        //自定义类使用系统类加载器加载 负责加载(jre/lib/ext下的jar包或-D java.ext.dirs 锁指定的目录下的jar包)
        //扩展类加载器 ClassLoaders$PlatformClassLoader
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //系统类引导其无法获得引导类加载器。
        //引导类加载器 null  主要负责加载java的核心类库
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

    }

    /**
     * Properties: 用来读取配置文件 工程下的配置文件在部署的时候会缺失。
     */
    @Test
    public void testClassLoader1() throws IOException {
        Properties properties = new Properties();

        //读取配置文件 方式一
        //此时文件默认在当前module下

//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        properties.load(fis);

        //读取配置文件 方式二
        //文件的默认位置在当前module的src下
        InputStream is = ClassLoaderTest.class.getClassLoader().getResourceAsStream("jdbc1.properties");
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println(user+" "+password);
    }
}
