package com.example.adapter.out;

import com.example.application.port.out.AccountOutputPort;

public class  AccountConsoleOutputAdapter implements AccountOutputPort {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
