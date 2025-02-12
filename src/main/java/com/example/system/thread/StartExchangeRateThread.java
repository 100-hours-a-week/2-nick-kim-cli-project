package com.example.system.thread;

import com.example.exchange.application.service.ExchangeRateCommandUseCase;

import java.util.Random;

public class StartExchangeRateThread extends Thread {
    private final ExchangeRateCommandUseCase exchangeRateCommandUseCase;
    private final ExchangeRateMonitor exchangeRateMonitor;

    public StartExchangeRateThread(ExchangeRateCommandUseCase exchangeRateCommandUseCase, ExchangeRateMonitor exchangeRateMonitor) {
        this.setDaemon(true);
        this.exchangeRateMonitor = exchangeRateMonitor;
        this.exchangeRateCommandUseCase = exchangeRateCommandUseCase;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double rate = 1250 + new Random(System.currentTimeMillis()).nextDouble() * (1500 - 1250);
            rate = Math.round(rate * 100.0) / 100.0;
            exchangeRateCommandUseCase.updateExchangeRate("USD", rate);
            exchangeRateMonitor.updateRate(rate);
        }
    }
}
