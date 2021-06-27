package com.futurell;

/**
 * @description:
 * @author: Mr.Li
 * @date: Created in 2021/6/24 7:49
 * @version: 1.0
 * @modified By:
 *
 * 得到的值不是50, M++ 不具有原子性
 */
public class InCreaseDemo {

    static volatile int M = 0;

    public synchronized static void increase() {
        M ++;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    increase();
                }
            }).start();
        }

        System.out.println(M);
    }

}
