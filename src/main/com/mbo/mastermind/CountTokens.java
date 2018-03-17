package com.mbo.mastermind;

/**
 * Tokens used to easily check for misplaced and right placed colors in user input
 */
public enum CountTokens {
    MISPLACED('?'),
    RIGHTPLACED('.');

    private char token;

    CountTokens(char token) {
        this.token = token;
    }

    public char getToken() {
        return this.token;
    }
}
