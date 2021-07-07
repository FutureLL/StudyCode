package com.futurell;

/**
 * @Description: 单例模式: 饿汉式
 * @Author: lilei58
 * @Date: Created in 2021/7/2 上午6:55
 */
public class HungrySingleton {

    /** 加载ClassLoader的时候就产生实例对象 */
    private static HungrySingleton instance = new HungrySingleton();

    public HungrySingleton() {
    }

    /** 返回实例对象 */
    public static HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton.getInstance());
            }).start();
        }
    }
}
