package com.设计模式.创建模式.工厂模式;

public class PlaneFactory extends VehicleFactory  {

    @Override
    public Movable createVehicle() {
        System.out.println("创建一个Plane的日志，权限等操作");
        return new Plane();
    }
}
