package com.example.system;

import com.example.account.application.port.in.AccountInputPort;
import com.example.account.application.port.out.AccountOutputPort;
import com.example.account.application.service.AccountCommandUseCase;
import com.example.account.application.service.AccountQueryUseCase;
import com.example.exchange.application.service.ExchangeRateCommandUseCase;
import com.example.exchange.application.service.ExchangeRateQueryUseCase;
import com.example.system.thread.ExchangeRateMonitor;
import com.example.system.thread.MonitoringThread;
import com.example.system.thread.StartExchangeRateThread;
import com.example.system.handler.ControllerMapping;
import com.example.system.handler.controller.BalanceInquiryController;
import com.example.system.handler.controller.CreateAccountController;
import com.example.system.handler.controller.DepositController;
import com.example.system.handler.controller.MenuController;
import com.example.system.handler.controller.WithdrawController;
import com.example.system.io.IOHandler;
import com.example.system.state.MainMenuState;
import com.example.system.state.MenuState;
import com.example.system.validate.AccountInputValidator;
import com.example.system.validate.AccountValueValidator;

import java.util.HashMap;
import java.util.Map;

public class AccountConsoleSystem {
    private final IOHandler ioHandler;
    private MenuState currentMenuState;

    public AccountConsoleSystem(AccountCommandUseCase accountCommandUseCase,
                                AccountQueryUseCase accountQueryUseCase,
                                ExchangeRateQueryUseCase exchangeRateQueryUseCase,
                                ExchangeRateCommandUseCase exchangeRateCommandUseCase,
                                AccountInputPort inputPort,
                                AccountOutputPort outputPort) {
        this.ioHandler = new IOHandler(inputPort, outputPort);
        ControllerMapping controllerMapping = initCommandRegistry(accountCommandUseCase, accountQueryUseCase, exchangeRateQueryUseCase, new AccountInputValidator(new AccountValueValidator(accountQueryUseCase), ioHandler));
        currentMenuState = new MainMenuState(controllerMapping,ioHandler);
        ExchangeRateMonitor exchangeRateMonitor = new ExchangeRateMonitor();
        new MonitoringThread(exchangeRateMonitor,ioHandler).start();
        new StartExchangeRateThread(exchangeRateCommandUseCase, exchangeRateMonitor).start();
    }

    private ControllerMapping initCommandRegistry(
            AccountCommandUseCase accountCommandUseCase,
            AccountQueryUseCase accountQueryUseCase,
            ExchangeRateQueryUseCase exchangeRateQueryUseCase,
            AccountInputValidator accountInputValidator) {
        Map<String, MenuController> menuCommands = new HashMap<>();
        menuCommands.put("DEPOSIT", new DepositController(accountCommandUseCase, accountInputValidator, ioHandler));
        menuCommands.put("WITHDRAW", new WithdrawController(accountCommandUseCase, accountInputValidator, ioHandler));
        menuCommands.put("BALANCE_INQUIRY", new BalanceInquiryController(accountQueryUseCase, exchangeRateQueryUseCase, accountInputValidator, ioHandler));
        menuCommands.put("CREATE_ACCOUNT", new CreateAccountController(accountCommandUseCase, accountInputValidator, ioHandler));
        return new ControllerMapping(menuCommands);
    }

    public void run() {
        ioHandler.printMessage("계좌 시스템입니다.");
        while (currentMenuState != null) {
            currentMenuState.display(); // 현재 메뉴 상태에서 출력
            String menuInput = ioHandler.getInput(); // 메뉴 인풋값 받기
            currentMenuState = currentMenuState.handleInput(menuInput); // 메뉴 핸들링
        }
        ioHandler.printMessage("시스템을 종료합니다.");
    }
}
