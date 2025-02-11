package com.example;


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
        AccountRepository accountRepositoryPort = new AccountMapDB();

        AccountQueryPersistenceAdapter accountQueryPersistenceAdapter = new AccountQueryPersistenceAdapter(accountRepositoryPort);
        AccountQueryUseCase accountQueryUseCase = new AccountQueryUseCase(accountQueryPersistenceAdapter);

        AccountCommandPersistenceAdapter accountCommandPersistenceAdapter = new AccountCommandPersistenceAdapter(accountRepositoryPort);
        AccountCommandUseCase accountCommandUseCase = new AccountCommandUseCase(accountCommandPersistenceAdapter);

        AccountInputPort accountInputPort = new AccountConsoleInputAdapter();
        AccountOutputPort accountOutputPort = new AccountConsoleOutputAdapter();

        return new AccountConsoleSystem(
                accountCommandUseCase,
                accountQueryUseCase,
                accountInputPort,
                accountOutputPort);
    }
}
