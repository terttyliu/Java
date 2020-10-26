package com.反射;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AOPTest {
    @Test
    public void testAOP() {

    }

}

class MyInvocation implements InvocationHandler {
    private Object obj;

    public void bind(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil util = new HumanUtil();
        util.method1();
        Object invoke = method.invoke(obj, args);
        util.method2();
        return invoke;

    }
}

class HumanUtil {
    public void method1() {
        System.out.println("1****************");
    }

    public void method2() {
        System.out.println("2*****************");
    }
}
