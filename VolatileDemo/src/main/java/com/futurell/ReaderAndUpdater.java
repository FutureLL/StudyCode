package com.futurell;

import java.util.concurrent.TimeUnit;

/**
 * @description: 用一个线程读数据,一个线程改数据
 * @author: Mr.Li
 * @date: Created in 2021/6/18 7:34
 * @version: 1.0
 * @modified By:
 *
 * 增加 volatile 就可以让 Reader 感知到 INIT_VALUE 在变化
 */
public class ReaderAndUpdater {

    private final static int MAX = 5;

    static volatile int INIT_VALUE = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX) {
                if (localValue != INIT_VALUE) {
                    System.out.println("The init value: " + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX) {
                System.out.println("The init value will be change to: " + (++localValue));
                INIT_VALUE = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
