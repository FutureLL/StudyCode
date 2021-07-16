package com.futurell;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description:
 * @Author: lilei58
 * @Date: Created in 2021/7/15 上午7:33
 */
public class MyLock implements Lock {

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer {

        /**
         * 获取锁
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            // 状态值为0,才可以被线程占有
            if (getState() == 0) {
                // 利用 CAS 原理修改 state
                if (compareAndSetState(0, arg)) {
                    // 设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            // LockDemo02 这种情况
            // 占有资源的线程是否等于当前线程
            else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                setState(getState() + arg);
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            // 只有当 state 为 0 时,表示其处于空闲状态
            if (state == 0) {
                setExclusiveOwnerThread(null);
                setState(state);
                return true;
            }
            // 释放锁之前已独占资源,因此不存在线程安全问题
            setState(state);
            return false;
        }

        public Condition newConditionObject() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 以中断的方式去获取锁
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
