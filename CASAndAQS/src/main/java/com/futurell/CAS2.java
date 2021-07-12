package com.futurell;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/12 上午6:59
 */
public class CAS2 {

    /** 不能保证原子性,因此多线程环境下,M < 20 */
    private static volatile int M = 0;

    /** AtomicInteger: 原子整型,默认初始值为 100 */
    private static AtomicInteger atomic = new AtomicInteger(100);

    private static AtomicStampedReference reference = new AtomicStampedReference(100 ,1);

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println(atomic.compareAndSet(100, 110));
            System.out.println(atomic.get());
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomic.compareAndSet(110, 100));
            System.out.println(atomic.get());
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomic.compareAndSet(100, 120));
            System.out.println(atomic.get());
        });
        thread3.start();

        try {
            thread.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=============================");

        Thread thread4 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(100, 110, reference.getStamp(), reference.getStamp() + 1));
            System.out.println(reference.compareAndSet(110, 100, reference.getStamp(), reference.getStamp() + 1));
        });

        Thread thread5 = new Thread(() -> {
            // 拿到最开始的版本号为 --- 1
            int stamp = reference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(reference.compareAndSet(100, 120, stamp, stamp + 1));
        });

        thread4.start();
        thread5.start();
    }
}
