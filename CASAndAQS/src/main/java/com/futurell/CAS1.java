package com.futurell;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/9 上午7:40
 *
 *  使用 javap 命令查看底层源码
 * 1. 进入到对应文件的目录: /Users/futurelwy/MyIdeaProjects/StudyCode/CASAndAQS/src/main/java/com/futurell
 * 2. 编译该文件: javac CAS1.java
 * 3. 查看 class 文件的字节码文件: javap -verbose CAS1
 *
 * public static void increase();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=0, args_size=0
 *       // 3 4 5 实现了 M++;
 *          0: getstatic     #2                  // Field M:I
 *          3: iconst_1
 *          4: iadd
 *          5: putstatic     #2                  // Field M:I
 *          8: return
 *       LineNumberTable:
 *         line 19: 0
 *         line 20: 8
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * public static void increaseAtomic();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=1, locals=0, args_size=0
 *       // 3 实现了 atomic++;
 *          0: getstatic     #3              // Field atomic:Ljava/util/concurrent/atomic/AtomicInteger;
 *          3: invokevirtual #4              // Method java/util/concurrent/atomic/AtomicInteger.incrementAndGet:()I
 *          6: pop
 *          7: return
 *       LineNumberTable:
 *         line 23: 0
 *         line 24: 7
 */
public class CAS1 {

    /** 不能保证原子性,因此多线程环境下,M < 20 */
    private static volatile int M = 0;

    /** AtomicInteger: 原子整型,默认初始值为 0 */
    private static AtomicInteger atomic = new AtomicInteger(0);

    public static void increase() {
        M ++;
    }

    public static void increaseAtomic() {
        atomic.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] t = new Thread[20];
        for (int i = 0; i < 20; i++) {
            t[i] = new Thread(() -> CAS1.increase());
            t[i].start();
            t[i].join();
        }

        System.out.println(M);

        Thread[] tf = new Thread[20];
        for (int i = 0; i < 20; i++) {
            tf[i] = new Thread(() -> CAS1.increaseAtomic());
            tf[i].start();
            // join(): 直到该线程执行结束才继续下一个线程的执行,使得线程间有了交互性
            tf[i].join();
        }

        System.out.println("atomic: " + atomic.get());
    }
}
