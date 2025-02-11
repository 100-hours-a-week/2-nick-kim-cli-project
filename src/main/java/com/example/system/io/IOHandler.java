package com.example.system.io;

import com.example.account.application.port.in.AccountInputPort;
import com.example.account.application.port.out.AccountOutputPort;

import java.util.List;

public class IOHandler {
    private final AccountInputPort inputPort;
    private final AccountOutputPort outputPort;

    public IOHandler(AccountInputPort inputPort, AccountOutputPort outputPort) {
        this.inputPort = inputPort;
        this.outputPort = outputPort;
    }

    public void printMessage(String message) {
        outputPort.print(message);
    }

    public String getInput() {
        return inputPort.inputString();
    }

    public void displayMenu(List<String> menuItems) {
        menuItems.forEach(this::printMessage);
    }
}