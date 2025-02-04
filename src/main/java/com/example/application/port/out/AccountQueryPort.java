package com.example.application.port.out;

import com.example.domain.Account;

public interface AccountQueryPort {
    int getBalance(long accountId, String password);
    boolean existAccount(long accountId);
}
