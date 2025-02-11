package com.example.account.adapter.out.persistence.db;

import com.example.account.adapter.out.persistence.repository.AccountRepository;
import com.example.account.domain.Account;

abstract class AccountInMemoryDB implements AccountRepository {

    private Long pk = 1L;

    abstract void putAccount(Account account);

    @Override
    public int getBalance(long accountId, String password) throws IllegalArgumentException {
        Account account = findAccountByIdAndPassword(accountId, password);
        return account.getBalance();
    }

    @Override
    public boolean deposit(long accountId, int amount) throws IllegalArgumentException  {
        Account account = findAccountById(accountId);
        return account.deposit(amount);
    }

    @Override
    public boolean withdraw(long accountId, String password, int amount) throws IllegalArgumentException {
        Account account = findAccountByIdAndPassword(accountId, password);
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

    protected Long currentPK() {
        return this.pk;
    }

    protected void increasePK() {
        this.pk++;
    }
}
