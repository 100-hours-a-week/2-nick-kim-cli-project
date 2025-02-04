package com.example.adapter.out.persistence;

import com.example.domain.Account;

public interface  AccountRepository {
    boolean withdraw(long accountId, String password, int amount);
    boolean deposit(long accountId,int amount);
    long createAccount(String password, int balance);
    Account findAccountById(long accountId);
    int getBalance(long accountId, String password);
}
