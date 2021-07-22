package com.futurell;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/22 上午7:32
 */
public class BlockDequeDemo {

    public static void main(String[] args) {
        LinkedBlockingDeque list = new LinkedBlockingDeque(3);

        // 插入元素
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    String str = new String(i + ": " + j);
                    try {
                        list.put(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client: " + str + " " + (new Date()));
                }
            }
        });
        thread.start();

        // 取元素
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    String str = String.valueOf(list.take());
                    System.out.println("main: take " + str + " , size: " + list.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("end");
    }
}
