package com.futurell;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Description: 单例模式: 双锁模式,两次检测加锁
 * @Author: lilei58
 * @Date: Created in 2021/7/7 上午6:33
 */
public class DCL {

    Connection connection;
    Socket socket;

    private volatile static DCL instance = null;

    public DCL() {
        /**
         * happens-before 指令重排
         * 下边的三条语句没有关系
         * 有可能将 instance = new DCL(); 语句放在开始执行
         * 线程1执行完 instance = new DCL(); 线程2拥有时间片,开始执行
         * 那么线程2在执行的时候,发现 instance 不为空了,然后继续执行 connection 以及 socket,
         * 但此时线程1还没有执行完,connection 以及 socket 都为空,就会报空指针异常 NullPointException
         */
        try {
            connection.close();
            socket.close();
            instance = new DCL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 返回实例对象 */
    public static DCL getInstance() {
        // 不能保证实例对象的唯一性
        if (null == instance) {
            synchronized(DCL.class) {
                if (null == instance) {
                    instance = new DCL();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(DCL.getInstance());
            }).start();
        }
    }
}
