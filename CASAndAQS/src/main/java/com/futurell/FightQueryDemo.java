package com.futurell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description: CountDownLatch 所有的结果都拿到才继续执行
 * @Author: lilei58
 * @Date: Created in 2021/7/16 上午8:38
 */
public class FightQueryDemo {

    private static List<String> company = Arrays.asList("东方航空", "南方航空", "海南航空");
    private static List<String> fightList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // 初始化地点
        String origin = "北京";
        String dest = "上海";

        // 三个航空公司三个线程,每个线程查询各自的航班信息
        Thread[] threads = new Thread[company.size()];
        // countDown(): 构造一个用给定计数初始化的 CountDownLatch
        // company.size(): 等待线程执行完的数量
        CountDownLatch latch = new CountDownLatch(company.size());

        for (int i = 0; i < threads.length; i++) {
            String name = company.get(i);
            threads[i] = new Thread(() -> {
                System.out.printf("%s 查询从 %s 到 %s 的机票\n", name, origin, dest);
                //随机产生票数
                int val = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(val);
                    // 将查询到的数据进行存储
                    fightList.add(name + "--" + val);
                    System.out.printf("%s 公司查询成功！\n", name);
                    // countDown(): 递减锁存器的计数,如果计数为零则释放所有等待的线程
                    // 以任务为单位
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        // await(): 当前线程在锁存器倒计数至零之前一直等待,除非线程被中断
        // 唤醒祝线程继续执行
        latch.await();
        System.out.println("==============查询结果如下：================");
        // 展示
        fightList.forEach(System.out::println);
    }
}
