package com.example.exchange.domain;

import java.time.LocalDateTime;

public class ExchangeRate {
    private double rate;
    private String currency;
    private LocalDateTime date;

    public ExchangeRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
        this.date = LocalDateTime.now();
    }

    public double getRate() {
        return rate;
    }

    public void updateRate(double rate) {
        this.rate = rate;
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDateTime() {
        return date;
    }
}
