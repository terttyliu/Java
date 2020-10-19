package design_pattern;

import java.util.Scanner;

/**
 * 客户端不关注子类从哪里来，只关注有没有某功能，如eat（）。
 * 客户端与IFood子类没有任何关联，所有的关联都是通过 FoodFactory 类完成的。
 * 对子类进行扩充时只需要修改Factory类即可。
 * @author `pjliu`
 * @date 2020/09/21
 */
interface Eatable {
    public void eat();
}

class Bread implements Eatable {

    @Override
    public void eat() {
        System.out.println("吃面包");
    }
}

class Milk implements Eatable {

    @Override
    public void eat() {
        System.out.println("喝牛奶");
    }
}

class EatFactory {
    public static Eatable getInstance(final String className) {
        if ("bread".equals(className)) {
            return new Bread();
        }
        if ("milk".equals(className)) {
            return new Milk();
        } else {
            return null;
        }
    }
}

public class FactoryPattern {
    public static void main(final String[] args) {
        // 此时程序出现耦合问题，关键字"new"是造成代码耦合的直接元凶。
        // IFood food=new Milk();
        // food.eat();
        final Scanner scanner = new Scanner(System.in);
        System.out.println("输入你要吃的东西");
        final String foodName = scanner.nextLine();
        scanner.close();
        final Eatable food = EatFactory.getInstance(foodName);
        if (food!=null){
            food.eat();
        }else {
            System.out.println("不存在这种食物");
        }
    }
}
