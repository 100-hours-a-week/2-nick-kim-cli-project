package com.example.system.handler;

import com.example.system.handler.controller.MenuController;

import java.util.Map;

public class ControllerMapping {
    private final Map<String, MenuController> commands;

    public ControllerMapping(Map<String, MenuController> commands) {
        this.commands = commands;
    }

    public MenuController getCommand(String command) {
        return commands.get(command);
    }
}
