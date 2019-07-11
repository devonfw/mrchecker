package com.capgemini.mrchecker.selenium.pages.projectY;

/**
 * Represents type of user interaction with an element
 */
public enum InteractionType {
    KEYBOARD(0),
    MOUSE(1);

    private final int methodValue;

    InteractionType(int methodValue) {
        this.methodValue = methodValue;
    }

    public int getMethodValue() {
        return methodValue;
    }
}

