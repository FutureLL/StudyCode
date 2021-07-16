package com.futurell;

/**
 * @Description: 可重入性
 * @Author: lilei58
 * @Date: Created in 2021/7/16 上午6:37
 */
public class LockDemo02 {

    private MyLock myLock = new MyLock();

    private static volatile int M = 0;

    public void a() {
        myLock.lock();
        System.out.println("a");
        b();
        myLock.unlock();

    }

    public void b() {
        myLock.lock();
        System.out.println("b");
        myLock.unlock();
    }

    public static void main(String[] args) {
        LockDemo02 demo = new LockDemo02();
        new Thread(() -> {
            demo.a();
        }).start();
    }
}
