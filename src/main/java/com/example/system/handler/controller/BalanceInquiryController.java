package com.example.system.handler.controller;

import com.example.account.application.service.AccountQueryUseCase;
import com.example.exchange.application.service.ExchangeRateQueryUseCase;
import com.example.exchange.domain.ExchangeRate;
import com.example.system.io.IOHandler;
import com.example.system.validate.AccountInputValidator;

import java.time.format.DateTimeFormatter;

public
class BalanceInquiryController implements MenuController {
    private final AccountQueryUseCase accountQueryUseCase;
    private final ExchangeRateQueryUseCase exchangeRateQueryUseCase;
    private final AccountInputValidator accountInputValidator;
    private final IOHandler ioHandler;

    public BalanceInquiryController(AccountQueryUseCase accountQueryUseCase,
                                    ExchangeRateQueryUseCase exchangeRateQueryUseCase,
                                    AccountInputValidator accountInputValidator,
                                    IOHandler ioHandler) {
        this.accountQueryUseCase = accountQueryUseCase;
        this.exchangeRateQueryUseCase = exchangeRateQueryUseCase;
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
        ExchangeRate exchangeRate = exchangeRateQueryUseCase.getUSDExchangeRate();
        String dateTime = exchangeRate.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ioHandler.printMessage(String.format("잔액: %d원 / USD 기준: %f$" , balance, exchangeKRWToUSD(balance, exchangeRate.getRate())));
        ioHandler.printMessage(String.format("%s 기준 환율: 1 USD = %f KRW" , dateTime, exchangeRate.getRate()));
    }

    private double exchangeKRWToUSD(int balance, double usdExchangeRate) {
        return 1 / usdExchangeRate * balance;
    }
}
