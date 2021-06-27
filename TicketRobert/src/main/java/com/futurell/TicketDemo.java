package com.futurell;

/**
 * @description: 叫号机程序
 * @author: Mr.Li
 * @date: Created in 2021/6/10 7:47
 * @version: 1.0
 * @modified By:
 *
 * 问题:
 * 1. 当并发量比较大的时候会出现: 跳号, 重号, 超过最大值的问题【重号, 超过最大值老版本会出现问题】
 *   重号: 该程序要经过三个步骤, 第一获取到某个 INDEX, 第二给输出, 第三这个 INDEX + 1,
 *         但是当这个线程还没有进行第三步, 此时 CPU 的使用权被其他线程争夺,
 *         因为当前 INDEX 的值没有变化还是 50, 所以新进来的线程取到的 INDEX 还是 50
 *   超过最大值: 该程序要经过三个步骤, 第一获取到某个 INDEX, 第二给输出, 第三这个 INDEX + 1,
 *         当程序执行到最后也就是此时 INDEX 为最大值, 比如: 500, 准备要执行第三步,
 *         但是现在 CPU 的使用权被其他线程争夺, Thread2 线程也进入到这个程序中,准备执行第一步,
 *         当 CPU 的使用权此时被原线程争夺后, 并且输出后, 此时 INDEX = 501, 执行完后,
 *         又被 Thread2 争夺, 该线程会继续执行, 获取到的 INDEX = 501, 输出为 501, 再 +1 程序结束
 *
 * synchronized 特性: 独占性、排他性、可见性
 */
public class TicketDemo extends Thread{

    /** 编号起始值 static: 四个 TicketDemo 对象共享一个 INDEX */
    private static int INDEX = 1;

    /** 编号的最大值 */
    private static final int MAX_INDEX = 500;

    @Override
    public void run() {
        synchronized (this) {
            while (INDEX <= MAX_INDEX) {
                System.out.println(Thread.currentThread().getName() + " 叫到的号码是：" + INDEX ++);
            }
        }
    }

    public static void main(String[] args) {
        TicketDemo t1 = new TicketDemo();
        TicketDemo t2 = new TicketDemo();
        TicketDemo t3 = new TicketDemo();
        TicketDemo t4 = new TicketDemo();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
