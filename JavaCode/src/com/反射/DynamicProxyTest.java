package com.反射;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.PreparedStatement;

/**
 * 反射的应用 动态代理
 * 静态代理的代理类和目标类在编译时期就确定下来，不利于程序的扩展。
 * 每一个代理类只能为一个接口服务，开发中会产生过多的代理。
 * 最好通过一个通用的代理类完成全部的代理功能
 * <p>
 * 动态代理是指通过代理类来调用其他对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对象
 * 使用场景：调试、远程方法调用
 * 优点：接口中声明的所有方法都被转移到调用处理器一个集中的方法处理，这样可以更加灵活统一的处理众多的方法。
 *
 * @author pjliu
 */
public class DynamicProxyTest {
    /**
     * 静态代理
     * 特点：代理类和被代理类在编译期间就已经确定
     */
    @Test
    public void testStaticProxy() {
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ProxyClothFactory clothFactory = new ProxyClothFactory(nikeClothFactory);
        clothFactory.produceCloth();
    }

    /**
     * 动态代理
     * 需要解决的问题
     * 问题一：如何根据加载到内存的被代理类，动态的创建一个代理类及其对象
     * 问题二：当通过代理类的对象调用方法时，如何动态的调用被代理类中的同名方法。
     */
    @Test
    public void testDynamicProxy() {
        Superman superman = new Superman();
        //ProxyInstance 代理类的对象
        Eatable instance = (Eatable) ProxyFactory.getProxyInstance(superman);
        ///通过代理类对象调用方法时，会调用被代理类中
        instance.eat();
        System.out.println("***********************");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory nike = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        nike.produceCloth();
    }
}

class ProxyFactory {
    //调用此方法，返回一个代理类的对象，解决问题一
    public static Object getProxyInstance(Object obj) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }
    public void connected(){
        System.out.println("正在建立链接");
    }
    public void disconnected(){
        System.out.println("正在释放链接");
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //当我们通过代理类的对象，调用方法a时候，就会调用invoke方法。
        if (obj == null) {
            return null;
        }
        connected();
        //被代理类的返回值
        Object returnValue = method.invoke(obj, args);
        disconnected();
        return returnValue;
    }
}

interface Eatable {
    void eat();
}

class Superman implements Eatable {

    @Override
    public void eat() {
        System.out.println("超人吃饭");
    }
}

interface ClothFactory {
    void produceCloth();
}

class ProxyClothFactory implements ClothFactory {
    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    private void prepareProduce() {
        System.out.println("获取原材料");
    }

    @Override
    public void produceCloth() {
        System.out.print("1 生产准备环节：");
        prepareProduce();
        System.out.print("2 生产环节：");
        factory.produceCloth();
        System.out.println("3 生产收尾环节：处理废水");
    }
}

class NikeClothFactory implements ClothFactory {
    @Test
    public void connected(){
        System.out.println("正在建立链接");
    }
    @Test
    public void disconnected(){
        System.out.println("正在释放链接");
    }

    @Override
    public void produceCloth() {
        System.out.println("Nike黑心工厂生产AJ");
    }
}


