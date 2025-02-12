package com.example.exchange.application.port.out;

public interface ExchangeRateCommandPort {
    void updateExchangeRate(String currency, double rate);
}
