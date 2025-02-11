package com.example.account.adapter.out.persistence.db;

import com.example.account.domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountMapDB extends AccountInMemoryDB {
    protected Map<Long, Account> db;

    public AccountMapDB() {
        db = new HashMap<>();
    }

    @Override
    public Account findAccountById(long accountId){
        Account account = this.db.getOrDefault(accountId, null);
        if(Objects.isNull(account)){
            throw new IllegalArgumentException(account.getAccountId() + "는 존재하지 않는 계좌번호입니다.");
        }
        return account;

    }

    @Override
    public Account findAccountByIdAndPassword(long accountId, String password) {
        Account account = findAccountById(accountId);
        if (!account.isSamePassword(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return account;
    }

    @Override
    protected void putAccount(Account account) {
        this.db.put(account.getAccountId(), account);
    }
}
