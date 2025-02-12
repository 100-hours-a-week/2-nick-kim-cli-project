package com.example.exchange.application.service;

import com.example.exchange.application.port.out.ExchangeRateQueryPort;
import com.example.exchange.domain.ExchangeRate;

public class ExchangeRateQueryUseCase {
    private final ExchangeRateQueryPort exchangeRateQueryPort;

    public ExchangeRateQueryUseCase(ExchangeRateQueryPort exchangeRateQueryPort) {
        this.exchangeRateQueryPort = exchangeRateQueryPort;
    }

    public ExchangeRate getUSDExchangeRate() {
        final String DOLLAR_NOTATION = "USD";
        return exchangeRateQueryPort.getUSDExchangeRate(DOLLAR_NOTATION);
    }
}
