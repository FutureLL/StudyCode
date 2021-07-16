package com.futurell;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/16 上午6:37
 */
public class  LockDemo01 {

    private MyLock myLock = new MyLock();

    private int M = 0;

    public int next() {
        myLock.lock();
        try {
            return M ++;
        } finally {
            myLock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo01 demo01 = new LockDemo01();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo01.next());
            });
            th[i].start();
        }
    }
}
