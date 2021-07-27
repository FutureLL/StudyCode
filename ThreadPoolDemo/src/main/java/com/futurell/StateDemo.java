package com.futurell;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/27 上午8:39
 */
public class StateDemo {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("COUNT_BITS: \t" + COUNT_BITS + " \t\t\t\t= " + Integer.toBinaryString(COUNT_BITS));
        System.out.println("CAPACITY: \t\t" + CAPACITY + " \t\t= " + Integer.toBinaryString(CAPACITY));
        System.out.println("RUNNING: \t\t" + RUNNING + " \t\t= " + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN: \t\t" + SHUTDOWN + " \t\t\t\t= 00" + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP: \t\t\t" + STOP + " \t\t= 00" + Integer.toBinaryString(STOP));
        System.out.println("TIDYING: \t\t" + TIDYING + " \t\t= 0" + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED: \t" + TERMINATED + " \t\t= 0" + Integer.toBinaryString(TERMINATED));
    }
}
