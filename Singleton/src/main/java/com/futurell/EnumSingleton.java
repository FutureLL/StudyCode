package com.futurell;

/**
 * @Description: 单例模式: 枚举
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午8:12
 */
public enum EnumSingleton {

    /** 常量,在加载的时候被实例化一次 */
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
