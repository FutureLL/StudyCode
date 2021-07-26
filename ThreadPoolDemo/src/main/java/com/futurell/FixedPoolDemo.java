package com.futurell;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/26 上午8:38
 */
public class FixedPoolDemo {

    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 创建10个任务给pool
        for (int i = 0; i < 10; i++) {
            // 创建任务
            Runnable task = new TaskDemo();
            // 把任务交给pool执行
            pool.execute(task);
        }

        // 关闭线程池
        pool.shutdown();
    }
}
