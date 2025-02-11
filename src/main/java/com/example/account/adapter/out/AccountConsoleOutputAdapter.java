package com.example.account.adapter.out;

import com.example.account.application.port.out.AccountOutputPort;

public class  AccountConsoleOutputAdapter implements AccountOutputPort {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
