package com.example.exchange.adapter.out;

import com.example.exchange.adapter.out.persistence.ExchangeRateRepository;
import com.example.exchange.application.port.out.ExchangeRateQueryPort;
import com.example.exchange.domain.ExchangeRate;

public class ExchangeRateQueryAdapter implements ExchangeRateQueryPort {
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateQueryAdapter(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }


    @Override
    public ExchangeRate getUSDExchangeRate(String currency) {
        return exchangeRateRepository.findExchangeRateByCurrency(currency);
    }
}
