package com.example;


import com.example.account.application.port.out.AccountCommandPort;
import com.example.account.application.port.out.AccountQueryPort;
import com.example.exchange.adapter.out.ExchangeRateCommandAdapter;
import com.example.exchange.adapter.out.ExchangeRateQueryAdapter;
import com.example.exchange.adapter.out.persistence.ExchangeRateRepository;
import com.example.exchange.adapter.out.persistence.ExchangeRateMapDB;
import com.example.exchange.application.port.out.ExchangeRateCommandPort;
import com.example.exchange.application.port.out.ExchangeRateQueryPort;
import com.example.exchange.application.service.ExchangeRateCommandUseCase;
import com.example.exchange.application.service.ExchangeRateQueryUseCase;
import com.example.system.AccountConsoleSystem;
import com.example.account.adapter.in.AccountConsoleInputAdapter;
import com.example.account.adapter.out.AccountConsoleOutputAdapter;
import com.example.account.adapter.out.persistence.AccountCommandPersistenceAdapter;
import com.example.account.adapter.out.persistence.db.AccountMapDB;
import com.example.account.adapter.out.persistence.AccountQueryPersistenceAdapter;
import com.example.account.adapter.out.persistence.repository.AccountRepository;
import com.example.account.application.port.in.AccountInputPort;
import com.example.account.application.port.out.AccountOutputPort;
import com.example.account.application.service.AccountCommandUseCase;
import com.example.account.application.service.AccountQueryUseCase;

public class Application {
    public static void main(String[] args) {
        AccountConsoleSystem accountConsoleSystem = initSystem();
        accountConsoleSystem.run();
    }

    private static AccountConsoleSystem initSystem() {
        AccountRepository accountRepository = new AccountMapDB();
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateMapDB();

        AccountQueryPort accountQueryPersistenceAdapter = new AccountQueryPersistenceAdapter(accountRepository);
        AccountQueryUseCase accountQueryUseCase = new AccountQueryUseCase(accountQueryPersistenceAdapter);

        AccountCommandPort accountCommandPersistenceAdapter = new AccountCommandPersistenceAdapter(accountRepository);
        AccountCommandUseCase accountCommandUseCase = new AccountCommandUseCase(accountCommandPersistenceAdapter);


        ExchangeRateQueryPort exchangeRateQueryPort = new ExchangeRateQueryAdapter(exchangeRateRepository);
        ExchangeRateQueryUseCase exchangeRateQueryUseCase = new ExchangeRateQueryUseCase(exchangeRateQueryPort);

        ExchangeRateCommandPort exchangeRateCommandPort = new ExchangeRateCommandAdapter(exchangeRateRepository);
        ExchangeRateCommandUseCase exchangeRateCommandUseCase = new ExchangeRateCommandUseCase(exchangeRateCommandPort);

        AccountInputPort accountInputPort = new AccountConsoleInputAdapter();
        AccountOutputPort accountOutputPort = new AccountConsoleOutputAdapter();

        return new AccountConsoleSystem(
                accountCommandUseCase,
                accountQueryUseCase,
                exchangeRateQueryUseCase,
                exchangeRateCommandUseCase,
                accountInputPort,
                accountOutputPort);
    }
}
