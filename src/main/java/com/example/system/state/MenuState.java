package com.example.system.state;

public interface MenuState {
    void display();

    MenuState handleInput(String input);
}
