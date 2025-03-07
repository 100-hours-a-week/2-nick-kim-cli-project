package com.example.account.application.service;

import com.example.account.application.port.out.AccountQueryPort;

public class AccountQueryUseCase {
    private final AccountQueryPort accountQueryPort;

    public AccountQueryUseCase(AccountQueryPort accountQueryPort) {
        this.accountQueryPort = accountQueryPort;
    }

    public int getBalance(long accountId, String password) throws IllegalArgumentException {
        return accountQueryPort.getBalance(accountId, password);
    }

    public boolean existAccount(long accountId) throws IllegalArgumentException {
        return accountQueryPort.existAccount(accountId);
    }
}