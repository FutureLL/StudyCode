package com.futurell;

/**
 * @description: 单例模式
 * @author: Mr.Li
 * @date: Created in 2021/6/25 7:20
 * @version: 1.0
 * @modified By:
 */
public class Singleton {

    private volatile static Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
