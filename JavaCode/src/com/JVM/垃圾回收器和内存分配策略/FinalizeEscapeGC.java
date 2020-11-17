package com.JVM.垃圾回收器和内存分配策略;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVA_HOOK = null;

    public void isAlive() {
        System.out.println("yes,i am still alive :) ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        SAVA_HOOK=this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVA_HOOK=new FinalizeEscapeGC();
        SAVA_HOOK=null;
        System.gc();
        Thread.sleep(500);
        if (SAVA_HOOK!=null){
            SAVA_HOOK.isAlive();
        }else {
            System.out.println("no, i am dead :( ");
        }
        SAVA_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVA_HOOK != null) {
            SAVA_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :( ");
        }
    }
}
