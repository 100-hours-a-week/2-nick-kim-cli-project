package com.example.system.handler.controller;

import com.example.account.application.service.AccountCommandUseCase;
import com.example.system.io.IOHandler;
import com.example.system.validate.AccountInputValidator;

public class WithdrawController implements MenuController {
    private final AccountCommandUseCase accountCommandUseCase;
    private final AccountInputValidator accountInputValidator;
    private final IOHandler ioHandler;

    public WithdrawController(AccountCommandUseCase accountCommandUseCase,
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

        ioHandler.printMessage("계좌 비밀번호(숫자 4글자)를 입력하세요:");
        String password = accountInputValidator.getValidPassword();

        ioHandler.printMessage("출금액을 입력하세요:");
        int amount = accountInputValidator.getValidAmount();

        accountCommandUseCase.withdraw(accountId, password, amount);
        ioHandler.printMessage("출금을 완료했습니다.");
    }
}