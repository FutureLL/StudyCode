package com.futurell;

/**
 * @Description: 单例模式: 懒汉式 + 同步方法
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午6:33
 */
public class HoonSynSingleton {

    private static HoonSynSingleton instance = null;

    public HoonSynSingleton() {
    }

    /** 返回实例对象 */
    public synchronized static HoonSynSingleton getInstance() {
        // 可以保证实例对象的唯一性
        if (null == instance) {
            instance = new HoonSynSingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HoonSynSingleton.getInstance());
            }).start();
        }
    }
}
