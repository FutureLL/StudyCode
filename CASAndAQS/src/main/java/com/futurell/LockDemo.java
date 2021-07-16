package com.futurell;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/15 上午8:51
 */
public class LockDemo {

    private int M = 0;

    public int next() {
        try {
            TimeUnit.SECONDS.sleep(2);
            return M ++;
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR");
        }
    }

    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }
}
