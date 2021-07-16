package com.futurell;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 可重入锁
 * @Author: lilei58
 * @Date: Created in 2021/7/16 上午6:37
 */
public class LockDemo03 {

    private ReentrantLock lock = new ReentrantLock();

    private static volatile int M = 0;

    public void a() {
        lock.lock();
        try {
            System.out.println("a");
            b();
        } catch (Exception e) {
            lock.unlock();
        }

    }

    public void b() {
        lock.lock();
        try {
            System.out.println("b");
        } catch (Exception e) {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo03 demo = new LockDemo03();
        new Thread(() -> {
            demo.a();
        }).start();
    }
}
