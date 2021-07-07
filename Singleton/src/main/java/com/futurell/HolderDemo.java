package com.futurell;

/**
 * @Description: 单例模式: Holder
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午7:53
 *
 * 在生命类的时候,成员变量中,不声明实例变量,而放到内部静态类中
 */
public class HolderDemo {

    public HolderDemo() {
    }

    /** 懒加载,静态类,只能实例化一次 */
    private static class Holder {
        // 不会主动实例化,只有在主动调用的时候才会进行实例化
        private static HolderDemo instance = new HolderDemo();
    }

    public static HolderDemo getInstance() {
        return Holder.instance;
    }
}
