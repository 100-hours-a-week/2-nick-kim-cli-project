package com.example.system.handler.controller;

import com.example.account.application.service.AccountCommandUseCase;
import com.example.system.io.IOHandler;
import com.example.system.validate.AccountInputValidator;

public class DepositController implements MenuController {
    private final AccountCommandUseCase accountCommandUseCase;
    private final AccountInputValidator accountInputValidator;
    private final IOHandler ioHandler;

    public DepositController(AccountCommandUseCase accountCommandUseCase,
                             AccountInputValidator accountInputValidator,
                             IOHandler ioHandler) {
        this.accountCommandUseCase = accountCommandUseCase;
        this.accountInputValidator = accountInputValidator;
        this.ioHandler = ioHandler;
    }

    @Override
    public void execute() {
        ioHandler.printMessage("계좌번호를 입력하세요:");
        long accountId = accountInputValidator.getValidAccountId();

        ioHandler.printMessage("입금액을 입력하세요:");
        int amount = accountInputValidator.getValidAmount();

        accountCommandUseCase.deposit(accountId, amount);
        ioHandler.printMessage("입금을 완료했습니다.");
    }
}