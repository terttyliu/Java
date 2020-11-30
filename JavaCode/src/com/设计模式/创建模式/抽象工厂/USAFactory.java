package com.设计模式.创建模式.抽象工厂;

public class USAFactory extends AbstractFactory {
    @Override
    public PC createPC() {
        //日志、权限管理
        return new USAPC();
    }

    @Override
    public TV createTV() {
        //日志、权限管理
        return new USATV();
    }
}
