#  锁

## hotSpot锁状态

![image-20201120161533736](https://raw.githubusercontent.com/terttyliu/Java/main/img/20201120161533.png)

## 锁升级过程

![image-20201120161155705](https://raw.githubusercontent.com/terttyliu/Java/main/img/20201120161155.png)

## Java对象头的组成

Java对象的对象头由 mark word 和 klass pointer 两部分组成，

mark word存储了同步状态、标识、hashcode、GC状态等等。

klass pointer存储对象的类型指针，该指针指向它的类元数据

值得注意的是，如果应用的对象过多，使用64位的指针将浪费大量内存。64位的JVM比32位的JVM多耗费50%的内存。

我们现在使用的64位 JVM会默认使用选项 +==UseCompressedOops== 开启指针压缩，将指针压缩至32位。

 

## 使用JOL工具类，打印对象头[#](https://www.cnblogs.com/LemonFive/p/11246086.html#2439469863)

使用maven的方式，添加jol依赖

```xml
<dependency>
  <groupId>org.openjdk.jol</groupId>
  <artifactId>jol-core</artifactId>
  <version>0.8</version>
</dependency>
```

 

创建一个对象A

```java
public class A {
    boolean flag = false;
}
```

 

使用jol工具类输出A对象的对象头

```java
public static void main(String[] args){
    A a = new A();
    System.out.println(ClassLayout.parseInstance(a).toPrintable());
}
```

看看输出结果

[![img](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165515515-995473102.png)](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165515515-995473102.png)

输出的第一行内容和锁状态内容对应

unused:1 | age:4 | biased_lock:1 | lock:2

   0      0000       0        01   代表A对象正处于无锁状态

 

第三行中表示的是被指针压缩为32位的klass pointer

第四行则是我们创建的A对象属性信息 1字节的boolean值

第五行则代表了对象的对齐字段 为了凑齐64位的对象，对齐字段占用了3个字节，24bit

 

## 偏向锁[#](https://www.cnblogs.com/LemonFive/p/11246086.html#2997523601)

```java
public static void main(String[] args) throws InterruptedException {
    Thread.sleep(5000);
    A a = new A();
    System.out.println(ClassLayout.parseInstance(a).toPrintable());
}
```

[![img]()](https://www.cnblogs.com/LemonFive/p/undefined) [![img]()](https://www.cnblogs.com/LemonFive/p/undefined)输出结果

[![img](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165646890-1940016592.png)](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165646890-1940016592.png)

刚开始使用这段代码我是震惊的，为什么睡眠了5s中就把活生生的A对象由无锁状态改变成为偏向锁了呢？别急，容我慢慢道来！

 

JVM启动时会进行一系列的复杂活动，比如装载配置，系统类初始化等等。在这个过程中会使用大量synchronized关键字对对象加锁，且这些锁大多数都不是偏向锁。为了减少初始化时间，JVM默认延时加载偏向锁。这个延时的时间大概为4s左右，具体时间因机器而异。当然我们也可以设置JVM参数 ==-XX:BiasedLockingStartupDelay=0== 来取消延时加载偏向锁。

 

可能你又要问了，我这也没使用synchronized关键字呀，那不也应该是无锁么？怎么会是偏向锁呢？

仔细看一下偏向锁的组成，对照输出结果红色划线位置，你会发现占用 thread 和 epoch 的 位置的均为0，说明当前偏向锁并没有偏向任何线程。此时这个偏向锁正处于可偏向状态，准备好进行偏向了！你也可以理解为此时的偏向锁是一个==**特殊状态的无锁**==。

[![img](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165715727-195445200.png)](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165715727-195445200.png)

 

大家可以看下面这张图理解一下对象头的状态的创建过程

[![img](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165937782-885586074.png)](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725165937782-885586074.png)

 

 

 

再来看看这段代码，使用了synchronized关键字

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```java
public static void main(String[] args) throws InterruptedException {
    Thread.sleep(5000);
    A a = new A();
    synchronized (a){
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

此时对象a，对象头内容有了明显的变化，当前偏向锁偏向主线程。

[![img](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725181301423-1283945859.png)](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725181301423-1283945859.png)

 

## 轻量级锁[#](https://www.cnblogs.com/LemonFive/p/11246086.html#2001132050)

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```java
public static void main(String[] args) throws Exception {
    Thread.sleep(5000);
    A a = new A();

    Thread thread1= new Thread(){
        @Override
        public void run() {
            synchronized (a){
                System.out.println("thread1 locking");
                out.println(ClassLayout.parseInstance(a).toPrintable()); //偏向锁
            }
        }
    };
    thread1.start();
    thread1.join();
    Thread.sleep(10000);

    synchronized (a){
        out.println("main locking");
        out.println(ClassLayout.parseInstance(a).toPrintable());//轻量锁
    }
}
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

thread1中依旧输出偏向锁，主线程获取对象A时，thread1虽然已经退出同步代码块，但主线程和thread1仍然为锁的交替竞争关系。故此时主线程输出结果为轻量级锁。[![img](https://img2020.cnblogs.com/blog/1274378/202004/1274378-20200413175408138-861530957.png)](https://img2020.cnblogs.com/blog/1274378/202004/1274378-20200413175408138-861530957.png)

 

## 重量级锁[#](https://www.cnblogs.com/LemonFive/p/11246086.html#1027547384)

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```java
public static void main(String[] args) throws InterruptedException {
    Thread.sleep(5000);
    A a = new A();
    Thread thread1 = new Thread(){
        @Override
        public void run() {
            synchronized (a){
                System.out.println("thread1 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                try {
                    //让线程晚点儿死亡，造成锁的竞争
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Thread thread2 = new Thread(){
        @Override
        public void run() {
            synchronized (a){
                System.out.println("thread2 locking");
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    thread1.start();
    thread2.start();
}
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void(0);)

thread1 和 thread2 同时竞争对象a，此时输出结果为重量级锁

[![img](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725181504941-897601378.png)](https://img2018.cnblogs.com/blog/1274378/201907/1274378-20190725181504941-897601378.png)