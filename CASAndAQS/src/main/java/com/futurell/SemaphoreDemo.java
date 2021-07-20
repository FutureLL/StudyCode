package com.futurell;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Semaphore 信号量(可以使用的资源量)
 * @Author: lilei58
 * @Date: Created in 2021/7/17 下午10:18
 *
 * 请求资源: acquire() 获取许可
 * 使用资源: 业务处理
 * 释放资源: release() 释放许可证,将其返回到信号量
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 创建一个 Semaphore 与给定数量的许可证和给定的公平设置
        Semaphore semaphoreFair = new Semaphore(2, true);

        // 创建一个 Semaphore 与给定数量的许可证和非公平公平设置
        Semaphore semaphore = new Semaphore(5);

        // 请求许可
        Thread[] car = new Thread[10];
        for (int i = 0; i < 10; i++) {
            car[i] = new Thread(() -> {
                try {
                    // 请求许可
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 可以进入停车场");

                    // 使用资源
                    int val = new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(val);
                    System.out.println(Thread.currentThread().getName() + " 停留了 " + val + " 秒");

                    // 释放资源
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " 离开停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Car[" + i + "]");
            car[i].start();
        }

    }
}
