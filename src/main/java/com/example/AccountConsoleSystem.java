package com.example;

import com.example.application.port.in.AccountInputPort;
import com.example.application.port.out.AccountOutputPort;
import com.example.application.service.AccountCommandUseCase;
import com.example.application.service.AccountQueryUseCase;

public class AccountConsoleSystem {
    private final AccountCommandUseCase accountCommandUseCase;
    private final AccountQueryUseCase accountQueryUseCase;
    private final AccountInputPort accountInputPort;
    private final AccountOutputPort accountOutputPort;

    public AccountConsoleSystem(
            AccountCommandUseCase accountCommandUseCase,
            AccountQueryUseCase accountQueryUseCase,
            AccountInputPort accountInputPort,
            AccountOutputPort accountOutputPort) {
        this.accountCommandUseCase = accountCommandUseCase;
        this.accountQueryUseCase = accountQueryUseCase;
        this.accountInputPort = accountInputPort;
        this.accountOutputPort = accountOutputPort;
    }

    public void run() {
        accountOutputPort.print("계좌 시스템입니다.");

        while (true) {
            displayMainMenu();
            String op = accountInputPort.inputString();

            if (handleMainMenuChoice(op)) {
                break;
            }
        }
        accountOutputPort.print("시스템을 종료합니다.");
    }

    private void displayMainMenu() {
        accountOutputPort.print("\n=== 메인 메뉴 ===");
        accountOutputPort.print("1. 입출금");
        accountOutputPort.print("2. 잔액조회");
        accountOutputPort.print("3. 계좌 생성");
        accountOutputPort.print("4. 종료");
        accountOutputPort.print("선택해주세요: ");
    }

    private boolean handleMainMenuChoice(String choice) {
        switch (choice) {
            case "1":
                handleTransactionMenu();
                return false;
            case "2":
                handleBalanceInquiry();
                return false;
            case "3":
                handleAccountCreation();
                return false;
            case "4":
                return true;
            default:
                accountOutputPort.print("잘못된 선택입니다. 다시 선택해주세요.");
                return false;
        }
    }

    private void handleTransactionMenu() {
        while (true) {
            accountOutputPort.print("\n=== 입출금 메뉴 ===");
            accountOutputPort.print("1. 입금");
            accountOutputPort.print("2. 출금");
            accountOutputPort.print("3. 뒤로가기");
            accountOutputPort.print("선택해주세요: ");

            String choice = accountInputPort.inputString();

            switch (choice) {
                case "1":
                    handleDeposit();
                    break;
                case "2":
                    handleWithdraw();
                    break;
                case "3":
                    return;
                default:
                    accountOutputPort.print("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private void handleDeposit() {
        try {
            long accountId = inputAccountId();
            int amount = inputInitBalance();

            if (!accountCommandUseCase.deposit(accountId, amount)) {
                accountOutputPort.print("입금을 실패했습니다.");
                return;
            }
            accountOutputPort.print("입금을 완료했습니다.");

        } catch (NumberFormatException e) {
            accountOutputPort.print("잘못된 입력입니다. 숫자를 입력해주세요.");
        } catch (IllegalArgumentException e) {
            accountOutputPort.print(e.getMessage());
        }
    }

    private void handleWithdraw() {
        long accountId = inputAccountId();
        String password = inputPassword();
        int amount = inputWithdrawAmount();

        try {
            accountCommandUseCase.withdraw(accountId, password, amount);
            accountOutputPort.print("출급을 완료했습니다.");
        } catch (IllegalArgumentException e) {
            accountOutputPort.print(e.getMessage());
        }
    }

    private void handleBalanceInquiry() {
        try {
            long accountId = inputAccountId();
            String password = inputPassword();

            int balance = accountQueryUseCase.getBalance(accountId, password);
            accountOutputPort.print(String.format("현재 잔액: %d원", balance));
        } catch (NumberFormatException e) {
            accountOutputPort.print("잘못된 입력입니다. 이전 메뉴로 돌아갑니다.");
        } catch (IllegalArgumentException e) {
            accountOutputPort.print(e.getMessage());
        }
    }

    private void handleAccountCreation() {
        String password = inputPassword();
        int initialBalance = inputInitBalance();

        try {
            long accountId = accountCommandUseCase.createAccount(password, initialBalance);
            accountOutputPort.print("생성된 계좌번호는 " + accountId + " 입니다.");
        } catch (IllegalArgumentException e) {
            accountOutputPort.print(e.getMessage());
        }

        accountOutputPort.print("계좌를 성공적으로 생성했습니다.");
    }

    private int inputInitBalance() {
        int initialBalance;
        while (true) {
            accountOutputPort.print("입금액을 입력하세요:");
            try {
                initialBalance = Integer.parseInt(accountInputPort.inputString());
                validateBalance(initialBalance);
                break;
            } catch (NumberFormatException e) {
                accountOutputPort.print("잘못된 입력입니다. 다시 입력하세요.");
            } catch (IllegalArgumentException e) {
                accountOutputPort.print(e.getMessage());
            }
        }
        return initialBalance;
    }

    private long inputAccountId() {
        long accountId;
        while (true) {
            try {
                accountOutputPort.print("계좌번호를 입력하세요:");
                accountId = Long.parseLong(accountInputPort.inputString());
                if (!accountQueryUseCase.existAccount(accountId)) {
                    throw new IllegalArgumentException(accountId + "는 존재하지 않는 계좌번호입니다.");
                }
                break;
            } catch (NumberFormatException e) {
                accountOutputPort.print("잘못된 입력입니다. 다시 입력하세요.");
            } catch (IllegalArgumentException e) {
                accountOutputPort.print(e.getMessage());
            }
        }
        return accountId;
    }

    private String inputPassword() {
        String password;
        while (true) {
            accountOutputPort.print("계좌 비밀번호(숫자 4글자)를 입력하세요:");
            try {
                password = accountInputPort.inputString();
                validatePassword(password);
                break;
            } catch (IllegalArgumentException e) {
                accountOutputPort.print(e.getMessage());
            }
        }
        return password;
    }

    private int inputWithdrawAmount() {
        int amount;
        while (true) {
            try {
                accountOutputPort.print("출금액을 입력하세요:");
                amount = Integer.parseInt(accountInputPort.inputString());

                if (isNotPositive(amount)) {
                    throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
                }
                break;
            } catch (NumberFormatException e) {
                accountOutputPort.print("잘못된 입력입니다. 다시 입력하세요.");
            } catch (IllegalArgumentException e) {
                accountOutputPort.print(e.getMessage());
            }
        }
        return amount;
    }

    private void validatePassword(String password) {
        if (password.length() != 4)
            throw new IllegalArgumentException("비밀번호는 네글자입니다.");
        try {
            Integer.parseInt(password);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("비밀번호는 숫자로 이루어져야 합니다.");
        }
    }

    private void validateBalance(int initalBalance) {
        if (!isLessThanMaxInitBalance(initalBalance))
            throw new IllegalArgumentException("초기금액은 100만원을 초과할 수 없습니다.");
        if (isNegative(initalBalance))
            throw new IllegalArgumentException("초기 입금액은 0 이상이어야 합니다.");
    }

    private boolean isLessThanMaxInitBalance(int initalBalance) {
        return initalBalance <= 1000000;
    }

    private boolean isNegative(int initialBalance) {
        return initialBalance < 0;
    }

    private static boolean isNotPositive(int amount) {
        return amount <= 0;
    }

    private boolean isNotNegative(int balance) {
        return balance >= 0;
    }
}