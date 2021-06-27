package com.futurell;

/**
 * @description:
 * @author: Mr.Li
 * @date: Created in 2021/6/25 6:45
 * @version: 1.0
 * @modified By:
 */
public class ShutDownDemo extends Thread {

    private volatile boolean started = false;

    @Override
    public void run() {
        while (started) {
            doWork();
        }
    }

    public void shutdown() {
        started = false;
    }

    private void doWork() {

    }

}
