package com.example.adapter.out.persistence;

import com.example.domain.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountMapDB extends AccountInMemoryDB {
    protected Map<Long, Account> db;

    public AccountMapDB() {
        this.db = new HashMap<Long, Account>();
    }

    @Override
    public Account findAccountById(long accountId){
        return this.db.getOrDefault(accountId, null);
    }

    @Override
    protected void putAccount(Account account) {
        this.db.put(account.getAccountId(), account);
    }
}
