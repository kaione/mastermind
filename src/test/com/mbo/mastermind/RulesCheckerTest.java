package com.mbo.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RulesCheckerTest {

    @Test
    void checkUserInput() {
        String authorizedColors = "RJBOVN";
        assertEquals(true, RulesChecker.checkUserInput(authorizedColors, "RRRR"));
        assertEquals(true, RulesChecker.checkUserInput(authorizedColors, "RJBO"));
        assertEquals(false, RulesChecker.checkUserInput(authorizedColors, "rjbo"));
        assertEquals(false, RulesChecker.checkUserInput(authorizedColors, "RJB"));
        assertEquals(false, RulesChecker.checkUserInput(authorizedColors, "RJBOO"));
        assertEquals(false, RulesChecker.checkUserInput(authorizedColors, "-('("));
        assertEquals(true, RulesChecker.checkUserInput("VN", "VNVN"));
        assertEquals(false, RulesChecker.checkUserInput("VN", "RRRR"));
    }

    @Test
    void countTokens() {
        assertEquals(1, RulesChecker.countTokens(".", '.'));
        assertEquals(2, RulesChecker.countTokens("..", '.'));
        assertEquals(2, RulesChecker.countTokens("..e", '.'));
        assertEquals(0, RulesChecker.countTokens("..e", '?'));
        assertEquals(0, RulesChecker.countTokens("", '?'));
    }

    @Test
    void processRightPlacedColors() {
        assertEquals("", RulesChecker.processRightPlacedColors("A", "AA"));
        assertEquals(".", RulesChecker.processRightPlacedColors("A", "A"));
        assertEquals("....", RulesChecker.processRightPlacedColors("AAAA", "AAAA"));
        assertEquals(".A..", RulesChecker.processRightPlacedColors("ABAA", "AAAA"));
        assertEquals(".A.B", RulesChecker.processRightPlacedColors("ABAA", "AAAB"));
    }

    @Test
    void processMisplacedColors() {
        assertEquals("", RulesChecker.processMisplacedColors("A", "AA"));
        assertEquals("????", RulesChecker.processMisplacedColors("ABCD", "BCDA"));
        assertEquals("??BA", RulesChecker.processMisplacedColors("ABCD", "BABA"));
        assertEquals("..BA", RulesChecker.processMisplacedColors("ABCD", "..BA"));
    }
}