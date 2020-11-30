package com.设计模式.创建模式.工厂模式;

/**
 * @author pjliu
 */
public class CarFactory extends VehicleFactory {

    @Override
    public Movable createVehicle() {
        System.out.println("创建一个car的日志，权限等操作");
        return new Car();
    }
}
