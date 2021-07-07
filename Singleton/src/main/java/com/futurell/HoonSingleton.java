package com.futurell;

/**
 * @Description: 单例模式: 懒汉式
 * @Author: lilei58
 * @Date: Created in 2021/7/2 上午7:23
 */
public class HoonSingleton {

    private static HoonSingleton instance = null;

    public HoonSingleton() {
    }

    /** 返回实例对象 */
    public static HoonSingleton getInstance() {
        // 不能保证实例对象的唯一性
        if (null == instance) {
            instance = new HoonSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HoonSingleton.getInstance());
            }).start();
        }
    }
}
