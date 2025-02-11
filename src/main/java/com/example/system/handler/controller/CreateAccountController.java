package com.example.system.handler.controller;

import com.example.account.application.service.AccountCommandUseCase;
import com.example.system.validate.AccountInputValidator;
import com.example.system.io.IOHandler;

public class CreateAccountController implements MenuController {
    private final AccountCommandUseCase accountCommandUseCase;
    private final AccountInputValidator accountInputValidator;
    private final IOHandler ioHandler;

    public CreateAccountController(AccountCommandUseCase accountCommandUseCase,
                                   AccountInputValidator accountInputValidator,
                                   IOHandler ioHandler) {
        this.accountCommandUseCase = accountCommandUseCase;
        this.accountInputValidator = accountInputValidator;
        this.ioHandler = ioHandler;
    }

    @Override
    public void execute() {
        ioHandler.printMessage("계좌 비밀번호(숫자 4글자)를 입력하세요:");
        String password = accountInputValidator.getValidPassword();

        ioHandler.printMessage("초기 입금액을 입력하세요:");
        int initialBalance = accountInputValidator.getValidAmount();

        long accountId = accountCommandUseCase.createAccount(password, initialBalance);
        ioHandler.printMessage("생성된 계좌번호는 " + accountId + " 입니다.");
        ioHandler.printMessage("계좌를 성공적으로 생성했습니다.");
    }
}