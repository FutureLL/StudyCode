package com.futurell;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/20 上午8:55
 */
public class CollectionDemo01 {

    public static void main(String[] args) throws InterruptedException {

        final ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque();

        // 添加数据
        Thread[] add = new Thread[100];
        for (int i = 0; i < 100; i++) {
            add[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.add(Thread.currentThread().getName() + "Element: " + j);
                }
            });
            add[i].start();
            add[i].join();
        }
        System.out.println("after size: " + list.size());

        // 移除数据
        Thread[] poll = new Thread[100];
        for (int i = 0; i < 100; i++) {
            poll[i] = new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    // 移除最后一位
                    list.pollLast();
                    // 移除首位
                    list.pollFirst();
                }
            });
            poll[i].start();
            poll[i].join();
        }
        System.out.println("after poll size: " + list.size());
    }
}
