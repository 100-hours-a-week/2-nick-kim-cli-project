package com.example.system.handler.controller;

import com.example.account.application.service.AccountQueryUseCase;
import com.example.system.validate.AccountInputValidator;
import com.example.system.io.IOHandler;

public
class BalanceInquiryController implements MenuController {
    private final AccountQueryUseCase accountQueryUseCase;
    private final AccountInputValidator accountInputValidator;
    private final IOHandler ioHandler;

    public BalanceInquiryController(AccountQueryUseCase accountQueryUseCase,
                                    AccountInputValidator accountInputValidator,
                                    IOHandler ioHandler) {
        this.accountQueryUseCase = accountQueryUseCase;
        this.accountInputValidator = accountInputValidator;
        this.ioHandler = ioHandler;
    }

    @Override
    public void execute() {
        ioHandler.printMessage("계좌번호를 입력하세요:");
        long accountId = accountInputValidator.getValidAccountId();

        ioHandler.printMessage("계좌 비밀번호(숫자 4글자)를 입력하세요:");
        String password = accountInputValidator.getValidPassword();

        int balance = accountQueryUseCase.getBalance(accountId, password);
        ioHandler.printMessage(String.format("현재 잔액: %d원", balance));
    }
}
