package com.futurell;

/**
 * @Description: 单例模式: 懒汉式 + 代码块
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午6:33
 */
public class HoonSynSingletonDemo {

    private static HoonSynSingletonDemo instance = null;

    public HoonSynSingletonDemo() {
    }

    /** 返回实例对象 */
    public static HoonSynSingletonDemo getInstance() {
        // 不能保证实例对象的唯一性
        if (null == instance) {
            synchronized(HoonSynSingletonDemo.class) {
                instance = new HoonSynSingletonDemo();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HoonSynSingletonDemo.getInstance());
            }).start();
        }
    }
}
