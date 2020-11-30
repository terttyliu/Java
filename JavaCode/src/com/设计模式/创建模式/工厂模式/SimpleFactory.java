package com.设计模式.创建模式.工厂模式;
/**
 * 简单工厂
 * 他的缺点是可扩展性不好。
 */
public class SimpleFactory {
    public Car createCar(){
        //权限处理,日志处理、权限处理等等~
        return new Car();
    }
    public Plane createPlane(){
        //权限处理,日志处理、权限处理等等~
        return  new Plane();
    }
}
