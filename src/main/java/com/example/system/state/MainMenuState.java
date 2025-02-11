package com.example.system.state;

import com.example.system.handler.ControllerMapping;
import com.example.system.io.IOHandler;

import java.util.Arrays;

public class MainMenuState implements MenuState {
    private final ControllerMapping controllerMapping;
    private final IOHandler ioHandler;

    public MainMenuState(ControllerMapping controllerMapping, IOHandler ioHandler) {
        this.controllerMapping = controllerMapping;
        this.ioHandler = ioHandler;
    }

    @Override
    public void display() {
        ioHandler.displayMenu(Arrays.asList(
                "=== 메인 메뉴 ===",
                "1. 입출금",
                "2. 잔액조회",
                "3. 계좌 생성",
                "4. 종료",
                "선택해주세요: "
        ));
    }

    @Override
    public MenuState handleInput(String input) {
        switch (input) {
            case "1":
                return new TransactionMenuState(controllerMapping, ioHandler);
            case "2":
                controllerMapping.getCommand("BALANCE_INQUIRY").execute();
                return this;
            case "3":
                controllerMapping.getCommand("CREATE_ACCOUNT").execute();
                return this;
            case "4":
                return null;
            default:
                ioHandler.printMessage("잘못된 선택입니다. 다시 선택해주세요.");
                return this;
        }
    }
}
