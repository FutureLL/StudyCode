package com.futurell;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午8:04
 */
public enum EnumDemo {

    /** 常量,在加载的时候被实例化一次 */
    A,B,C,D;

    public static void method() {
        System.out.println("method");
    }

    public static void main(String[] args) {
        A.method();
        B.method();
        C.method();
        D.method();
        System.out.println(A.getClass().getName());
        System.out.println(B.getClass().getName());
        System.out.println(C.getClass().getName());
        System.out.println(D.getClass().getName());
    }
}
