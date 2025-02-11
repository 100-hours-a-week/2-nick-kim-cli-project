package com.example.system.validate;

import com.example.account.application.service.AccountQueryUseCase;

public class AccountValueValidator {
    private final AccountQueryUseCase accountQueryUseCase;

    public AccountValueValidator(AccountQueryUseCase accountQueryUseCase) {
        this.accountQueryUseCase = accountQueryUseCase;
    }

    public long validateAccountIdAndGet(String input) {
        long accountId = 0L;
        try {
            accountId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력입니다. 숫자를 입력해주세요.");
        }
        if (!accountQueryUseCase.existAccount(accountId)) {
            throw new IllegalArgumentException(accountId + "는 존재하지 않는 계좌번호입니다.");
        }
        return accountId;
    }


    public String validatePasswordAndGet(String input) {
        if (input.length() != 4) {
            throw new IllegalArgumentException("비밀번호는 4자리 숫자여야 합니다.");
        }
        try {
            Integer.parseInt(input);
            return input;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("비밀번호는 4자리 숫자여야 합니다.");
        }
    }

    public int validateAmountAndGet(String input) {
        int amount = 0;
        try{
            amount = Integer.parseInt(input);
            if (amount <= 0 || amount > 1000000) {
                throw new IllegalArgumentException("금액은 0보다 크고 1000000 이하여야 합니다.");
            }
            return amount;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("금액은 숫자로 이루어져야 합니다.");
        }
    }
}
