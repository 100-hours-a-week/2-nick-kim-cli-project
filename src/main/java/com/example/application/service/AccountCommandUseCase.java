package com.example.application.service;

import com.example.application.port.out.AccountCommandPort;

public class AccountCommandUseCase {
    private final AccountCommandPort accountCommandPort;

    public AccountCommandUseCase(AccountCommandPort accountCommandPort) {
        this.accountCommandPort = accountCommandPort;
    }

    public long createAccount(String password, int balance) throws IllegalArgumentException{
        return accountCommandPort.createAccount(password, balance);
    }

    public boolean deposit(long accountId, int amount)  throws IllegalArgumentException {
        return accountCommandPort.deposit(accountId, amount);
    }

    public void withdraw(long accountId, String password, int amount) throws IllegalArgumentException {
        accountCommandPort.withdraw(accountId, password, amount);
    }
}
