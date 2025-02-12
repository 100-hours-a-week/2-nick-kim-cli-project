package com.example.system.thread;

import com.example.system.io.IOHandler;

public class MonitoringThread extends Thread {
    private final ExchangeRateMonitor monitor;
    private final IOHandler ioHandler;

    public MonitoringThread(ExchangeRateMonitor monitor, IOHandler ioHandler) {
        super();
        this.monitor = monitor;
        this.ioHandler = ioHandler;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            monitor.getLock().lock();
            try {
                monitor.getCondition().await();  // 대기 상태로 진입
                if (monitor.isOutOfBound()) {
                    ioHandler.printMessage("경고: 환율이 범위를 벗어났습니다! 현재 환율: " + monitor.getCurrentRate());
                }
            } catch (InterruptedException e) {
                ioHandler.printMessage("MonitoringThread가 중단되었습니다.");
                break;
            } finally {
                monitor.getLock().unlock();
            }
        }
    }
}