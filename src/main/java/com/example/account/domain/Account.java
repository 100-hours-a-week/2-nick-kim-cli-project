package com.example.account.domain;

public class  Account {
    private long accountId;
    private String password;
    private int balance;

    public Account(long accountId, String password, int balance) {
        this.accountId = accountId;
        this.password = password;
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public int getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount > 1000000) {
            throw new RuntimeException("Amount exceeds 1000000");
        }
        this.balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if(this.balance < amount) {
            throw new RuntimeException("Amount exceeds balance");
        }
        this.balance -= amount;
        return true;
    }

    public boolean isSamePassword(String password) {
        return this.password.equals(password);
    }
}
