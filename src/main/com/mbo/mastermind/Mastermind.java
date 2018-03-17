package com.mbo.mastermind;

public class Mastermind {
    public static void main(String[] args) {
        Board board = new Board();

        try {
            board.launchGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}

