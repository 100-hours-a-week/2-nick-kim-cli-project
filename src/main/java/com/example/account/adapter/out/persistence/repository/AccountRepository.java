package com.example.account.adapter.out.persistence.repository;

import com.example.account.domain.Account;

public interface  AccountRepository {
    boolean withdraw(long accountId, String password, int amount);
    boolean deposit(long accountId,int amount);
    long createAccount(String password, int balance);
    Account findAccountById(long accountId);
    Account findAccountByIdAndPassword(long accountId, String password);
    int getBalance(long accountId, String password);
}
