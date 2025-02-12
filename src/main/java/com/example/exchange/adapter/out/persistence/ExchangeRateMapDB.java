package com.example.exchange.adapter.out.persistence;

import com.example.exchange.domain.ExchangeRate;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateMapDB implements ExchangeRateRepository {
    public Map<String, ExchangeRate> db;

    public ExchangeRateMapDB() {
        db = new HashMap<>();
        db.put("USD",new ExchangeRate("USD", 1453.73));
    }

    @Override
    public ExchangeRate findExchangeRateByCurrency(String currency) {
        ExchangeRate exchangeRate = db.getOrDefault(currency, null);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Currency not found");
        }
        return exchangeRate;
    }

    @Override
    public void updateExchangeRate(String currency, double rate) {
        ExchangeRate exchangeRateByCurrency = findExchangeRateByCurrency(currency);
        exchangeRateByCurrency.updateRate(rate);
        db.put(currency,exchangeRateByCurrency);
    }
}
