package com.设计模式.创建模式.抽象工厂;

/**
 * 抽象工厂用于任意定义产品一族(比如国产产品、进口产品)
 * 假设我们需要对某一族的产品进行检查。
 * 每一族有电视，电脑，显示器()
 *
 * @author pjliu
 */
public abstract class AbstractFactory {
    public abstract PC createPC();

    public abstract TV createTV();
}
