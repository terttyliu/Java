package com.设计模式.创建模式.抽象工厂;

import org.junit.jupiter.api.Test;

public class AFTest {
    @Test
    public void testChina() {
        AbstractFactory cF = new USAFactory();
        PC pc = cF.createPC();
        TV tv = cF.createTV();
        pc.play();
        tv.watch();
        System.out.println("*********** " + cF.getClass().getSimpleName() + " *************");
    }

    @Test
    public void testUSA() {
        //只需要将 testChina 第一行的 改为         AbstractFactory cF = new USAFactory();
    }
}
