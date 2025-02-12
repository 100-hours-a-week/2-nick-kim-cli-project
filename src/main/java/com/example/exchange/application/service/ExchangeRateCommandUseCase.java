package com.example.exchange.application.service;

import com.example.exchange.application.port.out.ExchangeRateCommandPort;

public class ExchangeRateCommandUseCase {
    private final ExchangeRateCommandPort exchangeRateCommandPort;

    public ExchangeRateCommandUseCase(ExchangeRateCommandPort exchangeRateCommandPort) {
        this.exchangeRateCommandPort = exchangeRateCommandPort;
    }

    public void updateExchangeRate(String currency, double rate) {
        exchangeRateCommandPort.updateExchangeRate(currency, rate);
    }
}
