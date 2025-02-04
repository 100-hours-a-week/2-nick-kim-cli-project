package com.example.adapter.out.persistence;

import com.example.application.port.out.AccountCommandPort;
import com.example.application.port.out.AccountQueryPort;
import com.example.domain.Account;

import java.util.Objects;

public class AccountQueryPersistenceAdapter implements AccountQueryPort {
    private final AccountRepository accountRepository;

    public AccountQueryPersistenceAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public int getBalance(long accountId, String password) {
        Account account = accountRepository.findAccountById(accountId);
        account.checkPassword(password);
        return accountRepository.getBalance(accountId, password);
    }

    @Override
    public boolean existAccount(long accountId) {
        Account account = accountRepository.findAccountById(accountId);
        return Objects.nonNull(account);
    }
}
