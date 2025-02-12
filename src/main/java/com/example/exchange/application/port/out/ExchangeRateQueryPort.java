package com.example.exchange.application.port.out;

import com.example.exchange.domain.ExchangeRate;

public interface ExchangeRateQueryPort {
    ExchangeRate getUSDExchangeRate(String currency);
}
