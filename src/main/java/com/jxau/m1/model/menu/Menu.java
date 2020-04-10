package com.jxau.m1.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<AbstractButton> buttons = new ArrayList<>();

    public Menu() {

    }

    public List<AbstractButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<AbstractButton> buttons) {
        this.buttons = buttons;
    }
}
