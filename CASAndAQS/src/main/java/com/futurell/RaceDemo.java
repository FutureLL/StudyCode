package com.futurell;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Description: CyclicBarrier
 * @Author: lilei58
 * @Date: Created in 2021/7/16 上午8:39
 *
 * 每个线程执行时,都会碰到一个屏障,知道所有线程执行结束,然后屏障便会打开,使得所有线程同时继续往下执行
 */
public class RaceDemo {

    private static Integer PERSON_COUNT = 8;

    public static void main(String[] args) {
        // 定义回环栅栏 且 指定数量
        CyclicBarrier barrier = new CyclicBarrier(PERSON_COUNT);

        // 定义线程数
        Thread[] play = new Thread[PERSON_COUNT];
        // 以此执行线程
        for (int i = 0; i < PERSON_COUNT; i++) {
            play[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println(Thread.currentThread().getName() + "准备好了");
                    // 等待所有线程都在此屏障上调用了 await() 方法
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 所有线程同时执行
                System.out.println("选手" + Thread.currentThread().getName() + "起跑");
            }, "play[" + i + "]");
            play[i].start();
        }
    }
}
