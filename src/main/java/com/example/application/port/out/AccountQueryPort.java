package com.example.application.port.out;

public interface AccountQueryPort {
    int getBalance(long accountId, String password);
    boolean existAccount(long accountId);
}
