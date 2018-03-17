package com.mbo.mastermind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTurnTest {

    private UserTurn userTurn;

    @BeforeEach
    void setUp() {
        this.userTurn = new UserTurn(1, 10);
    }

    @Test
    void turnToString() {
        assertEquals("|....| . | . | 1/10 |", this.userTurn.turnToString());
        this.userTurn.setProposition("RRRR");
        this.userTurn.setMisplacedColors(0);
        this.userTurn.setRightPlacedColors(4);
        assertEquals("|RRRR| 0 | 4 | 1/10 |", this.userTurn.turnToString());
    }

    @Test
    void setProposition() {
        this.userTurn.setProposition("RRRR");
        assertEquals("|RRRR| . | . | 1/10 |", this.userTurn.turnToString());
        this.userTurn.setProposition("AAAA");
        assertEquals("|AAAA| . | . | 1/10 |", this.userTurn.turnToString());
    }

    @Test
    void setMisplacedColors() {
        this.userTurn.setMisplacedColors(4);
        assertEquals("|....| 4 | . | 1/10 |", this.userTurn.turnToString());
        this.userTurn.setMisplacedColors(0);
        assertEquals("|....| 0 | . | 1/10 |", this.userTurn.turnToString());
    }

    @Test
    void setRightPlacedColors() {
        this.userTurn.setRightPlacedColors(4);
        assertEquals("|....| . | 4 | 1/10 |", this.userTurn.turnToString());
        this.userTurn.setRightPlacedColors(0);
        assertEquals("|....| . | 0 | 1/10 |", this.userTurn.turnToString());
    }

    @Test
    void getRightPlacedColors() {
        this.userTurn.setRightPlacedColors(4);
        assertEquals(4, this.userTurn.getRightPlacedColors());
        this.userTurn.setRightPlacedColors(2);
        assertEquals(2, this.userTurn.getRightPlacedColors());
    }

    @Test
    void isPlayed() {
        assertEquals(false, this.userTurn.isPlayed());
        this.userTurn.setProposition("AAAA");
        assertEquals(true, this.userTurn.isPlayed());
    }
}