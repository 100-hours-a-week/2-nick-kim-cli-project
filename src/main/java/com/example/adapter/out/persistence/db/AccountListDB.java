package com.example.adapter.out.persistence.db;

import com.example.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountListDB extends AccountInMemoryDB {
    protected List<Account> db;

    public AccountListDB() {
        db = new ArrayList<>();
    }

    @Override
    public Account findAccountById(long accountId){
        return this.db.stream()
                .filter(x -> x.getAccountId()==accountId)
                .findFirst()
                .orElse(null);
    }

    @Override
    protected void putAccount(Account account) {
        this.db.add(account);
    }
}
