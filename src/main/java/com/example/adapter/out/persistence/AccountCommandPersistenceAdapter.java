package com.example.adapter.out.persistence;

import com.example.adapter.out.persistence.repository.AccountRepository;
import com.example.application.port.out.AccountCommandPort;

public class AccountCommandPersistenceAdapter implements AccountCommandPort {
    private final AccountRepository accountRepository;

    public AccountCommandPersistenceAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean withdraw(long accountId, String password, int amount) {
        return accountRepository.withdraw(accountId, password, amount);
    }

    @Override
    public boolean deposit(long accountId, int amount) {
        return accountRepository.deposit(accountId, amount);
    }

    @Override
    public long createAccount(String password, int balance) throws IllegalArgumentException {
        return accountRepository.createAccount(password, balance);
    }
}
