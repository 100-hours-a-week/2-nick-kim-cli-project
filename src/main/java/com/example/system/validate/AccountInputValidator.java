package com.example.system.validate;

import com.example.system.io.IOHandler;

public class AccountInputValidator {
    private final IOHandler ioHandler;
    private final AccountValueValidator accountValueValidator;

    public AccountInputValidator(AccountValueValidator accountValueValidator, IOHandler ioHandler) {
        this.accountValueValidator = accountValueValidator;
        this.ioHandler = ioHandler;
    }

    public long getValidAccountId() {
        while (true) {
            try {
                return accountValueValidator.validateAccountIdAndGet(ioHandler.getInput());
            } catch (IllegalArgumentException e) {
                ioHandler.printMessage(e.getMessage());
            }
        }
    }

    public String getValidPassword() {
        while (true) {
            try {
                return accountValueValidator.validatePasswordAndGet(ioHandler.getInput());
            } catch (IllegalArgumentException e) {
                ioHandler.printMessage(e.getMessage());
            }
        }
    }

    public int getValidAmount() {
        while (true) {
            try {
                return accountValueValidator.validateAmountAndGet(ioHandler.getInput());
            } catch (IllegalArgumentException e) {
                ioHandler.printMessage(e.getMessage());
            }
        }
    }
}

