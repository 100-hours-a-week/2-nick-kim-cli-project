package com.example.application.port.out;

public interface AccountCommandPort {
    boolean withdraw(long accountId, String password, int amount);
    boolean deposit(long accountId, int amount);
    long createAccount(String password, int balance);
}
