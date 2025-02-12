package com.example.exchange.adapter.out.persistence;

import com.example.exchange.domain.ExchangeRate;

public interface ExchangeRateRepository {
    ExchangeRate findExchangeRateByCurrency(String currency);

    void updateExchangeRate(String currency, double rate);
}
