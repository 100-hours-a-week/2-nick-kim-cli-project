package com.example.adapter.out.persistence.db;

import com.example.adapter.out.persistence.repository.AccountRepository;
import com.example.domain.Account;

import java.util.Objects;

abstract class AccountInMemoryDB implements AccountRepository {

    private Long pk = 1L;

    abstract void putAccount(Account account);

    @Override
    public int getBalance(long accountId, String password) throws IllegalArgumentException {
        Account account = findAccountById(accountId);
        validateAccount(account);
        validatePassword(account, password);
        return account.getBalance();
    }

    @Override
    public boolean deposit(long accountId, int amount) throws IllegalArgumentException  {
        Account account = findAccountById(accountId);
        validateAccount(account);
        return account.deposit(amount);
    }

    @Override
    public boolean withdraw(long accountId, String password, int amount) throws IllegalArgumentException {
        Account account = findAccountById(accountId);
        validateAccount(account);
        validatePassword(account, password);
        return account.withdraw(amount);
    }

    @Override
    public long createAccount(String password, int balance) throws IllegalArgumentException{
        Long accountId = currentPK();
        increasePK();

        Account account;
        account = new Account(accountId, password, balance);

        putAccount(account);
        return account.getAccountId();
    }

    private void validateAccount(Account account) throws IllegalArgumentException  {
        if (Objects.isNull(account)) {
            throw new IllegalArgumentException(account.getAccountId() + "는 존재하지 않는 계좌번호입니다.");
        }
    }

    private void validatePassword(Account account, String password) throws IllegalArgumentException  {
        if (!account.checkPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }

    protected Long currentPK() {
        return this.pk;
    }

    protected void increasePK() {
        this.pk++;
    }
}
