package com.example.system.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExchangeRateMonitor {
    private static final double MIN_RATE = 1300.0;
    private static final double MAX_RATE = 1400.0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile double currentRate = 0.0;

    public void updateRate(double newRate) {
        lock.lock();
        try{
            this.currentRate = newRate;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public double getCurrentRate() {
        lock.lock();
        try {
            return currentRate;
        } finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public boolean isOutOfBound() {
        return currentRate > MAX_RATE || currentRate < MIN_RATE;
    }
}
