package com.futurell;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/26 上午8:38
 */
public class TaskDemo implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is running");
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
