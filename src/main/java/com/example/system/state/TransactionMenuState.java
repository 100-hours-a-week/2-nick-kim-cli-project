package com.example.system.state;

import com.example.system.handler.ControllerMapping;
import com.example.system.io.IOHandler;

import java.util.Arrays;

public class TransactionMenuState implements MenuState {
    private final ControllerMapping controllerMapping;
    private final IOHandler ioHandler;

    public TransactionMenuState(ControllerMapping controllerMapping, IOHandler ioHandler) {
        this.controllerMapping = controllerMapping;
        this.ioHandler = ioHandler;
    }

    @Override
    public void display() {
        ioHandler.displayMenu(Arrays.asList(
                "\n=== 입출금 메뉴 ===",
                "1. 입금",
                "2. 출금",
                "3. 뒤로가기",
                "선택해주세요: "
        ));
    }

    @Override
    public MenuState handleInput(String input) {
        switch (input) {
            case "1":
                controllerMapping.getCommand("DEPOSIT").execute();
                return this;
            case "2":
                controllerMapping.getCommand("WITHDRAW").execute();
                return this;
            case "3":
                return new MainMenuState(controllerMapping, ioHandler);
            default:
                ioHandler.printMessage("잘못된 선택입니다. 다시 선택해주세요.");
                return this;
        }
    }
}