package com.futurell;

/**
 * @Description: 单例模式: 枚举
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午8:15
 *
 * 设计上和饿汉式没有区别,只是设计上更巧妙了一些
 */
public class EnumSingletonDemo {

    private EnumSingletonDemo() {
    }

    /**
     * 内部类使用了 -- 延迟加载
     * 懒加载,只有在主动调用的时候才去实例化
     */
    private enum EnumHolder {
        /** 常量,在加载的时候被实例化一次 */
        INSTANCE;

        /** INSTANCE 本身就是常量,所以 instance 不需要加 static */
        private EnumSingletonDemo instance;

        EnumHolder() {
            this.instance = new EnumSingletonDemo();
        }

        private EnumSingletonDemo getInstance() {
            return instance;
        }
    }

    public static EnumSingletonDemo getInstance() {
        return EnumHolder.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(EnumSingletonDemo.getInstance());
            }).start();
        }
    }
}
