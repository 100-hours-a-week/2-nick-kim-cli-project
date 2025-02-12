package com.example.exchange.adapter.out;

import com.example.exchange.adapter.out.persistence.ExchangeRateRepository;
import com.example.exchange.application.port.out.ExchangeRateCommandPort;

public class ExchangeRateCommandAdapter implements ExchangeRateCommandPort {
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateCommandAdapter(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void updateExchangeRate(String currency, double rate) {
        exchangeRateRepository.updateExchangeRate(currency, rate);
    }
}
