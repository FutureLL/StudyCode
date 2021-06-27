package com.futurell;

import java.util.concurrent.TimeUnit;

/**
 * @description: SynchronizedDemo01
 * @author: Mr.Li
 * @date: Created in 2021/6/11 8:16
 * @version: 1.0
 * @modified By:
 */
public class SynchronizedDemo01 {

    // 修饰静态方法
    public synchronized void accessResources0() {
        try {
            synchronized (this) {
                int i = 10;
            }
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 修饰非静态方法
    public synchronized void accessResources1() {
        try {
            TimeUnit.MINUTES.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 代码块 this
    public void accessResources2() {
        // this: 表示当前对象,也就是谁调用就是谁
        synchronized (this) {
            try {
                synchronized (this) {
                    TimeUnit.SECONDS.sleep(2);
                }
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 代码块 class
    public void accessResources3() {
        // 类对象
        synchronized (SynchronizedDemo01.class) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        // 静态
        // for (int i = 0; i < 5; i++) {
        //     new Thread(SynchronizedDemo01::accessResources0).start();
        // }

        // 非静态
        SynchronizedDemo01 demo01 = new SynchronizedDemo01();
        for (int i = 0; i < 5; i++) {
            new Thread(demo01::accessResources1).start();
        }

        // 代码块
        // SynchronizedDemo01 demo02 = new SynchronizedDemo01();
        // for (int i = 0; i < 5; i++) {
        //     new Thread(demo02::accessResources2).start();
        // }
    }
}
