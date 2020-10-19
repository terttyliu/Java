package design_pattern;

/**
 * 代理设计的主要功能是将所有的开发注意力只集中在核心业务功能上。
 * 代理设计的主要特点是：一个接口提供有两个子类，其中一个子类是真实业务操作类，另一个是代理业务操作类。没有代理业务操作类，真实业务无法运行。
 * @author `pjliu`
 * @date 2020/09/21
 */

class EatReal implements Eatable {

    @Override
    public void eat() {
        System.out.println("[真实主题]");
    }
}

class EatProxy implements Eatable {
    private final Eatable eat;

    public EatProxy(final Eatable eat) {
        this.eat = eat;
    }

    public void prepare() {
        System.out.println("[代理主题]1.购买食材");
    }

    public void clear() {
        System.out.println("[代理主题]2.收拾碗筷");
    }

    @Override
    public void eat() {
        this.prepare();
        eat.eat();
        this.clear();
    }
}

/**
 * @author pjliu
 */
public class ProxyPattern {
    public static void main(final String[] args) {
        final Eatable aEat = new EatProxy(new EatReal());
        aEat.eat();
    }
}
