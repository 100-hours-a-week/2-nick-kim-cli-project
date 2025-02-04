package com.example.adapter.in;

import com.example.application.port.in.AccountInputPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountConsoleInputAdapter implements AccountInputPort {
    @Override
    public String inputString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
