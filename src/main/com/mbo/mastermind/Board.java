package com.mbo.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The board handling the game loop
 */
public class Board {
    private String authorizedColors = "RJBOVN";
    private String combination = "";
    private List<UserTurn> userTurnsList = new ArrayList<UserTurn>();
    private int maxUserTurns = 10;

    /**
     * Generate a new color combination for the user to find.
     * Generate user turns list waiting to be filled
     */
    Board() {
        for (int i = 0; i < 4; i++) {
            this.combination += this.getRandomColor();
        }
        for (int i = 0; i < this.maxUserTurns; i++) {
            this.userTurnsList.add(new UserTurn(i + 1, this.maxUserTurns));
        }
    }

    /**
     * @return  char representing a random color
     */
    private char getRandomColor() {
        return this.authorizedColors.charAt(ThreadLocalRandom.current().nextInt(0, this.authorizedColors.length() - 1));
    }

    /**
     * Prints the user turns list until printing a turn that has not yet been played
     */
    private void printUserTurns() {
        System.out.println("|-------------------|");
        for (int i = 0; i < this.userTurnsList.size(); i++) {
            UserTurn currentTurn = this.userTurnsList.get(i);
            System.out.println(currentTurn.turnToString());
            if (!currentTurn.isPlayed()) {
                i = this.userTurnsList.size();
            }
        }
        System.out.println("|-------------------|");
    }

    /**
     * Prints error message in case the user input is not valid
     */
    private void printUserInputError() {
        System.out.println("La combinaison entrée n'est pas valide, veuillez entrer 4 lettres (ni plus ni moins) parmis les suivantes:");
        System.out.println("- R pour rouge");
        System.out.println("- J pour jaune");
        System.out.println("- B pour bleu");
        System.out.println("- O pour orange");
        System.out.println("- V pour vert");
        System.out.println("- N pour noir");
    }

    /**
     * Handles the user input gathering and game loop
     */
    public void launchGame() {
        Scanner reader = new Scanner(System.in);
        System.out.println(this.combination);
        System.out.println("Ordinateur > J'ai choisit ma combinaison, à vous de deviner!\nLes couleurs possibles sont R J B O V et N.\nTapez RJBO pour tenter les couleurs R, J, B et O dans l'ordre.\nVoici la grille actuelle:");
        try {
            for (int i = 0; i < this.maxUserTurns; i++) {
                this.printUserTurns();
                System.out.print("Vous > ");
                String userInput = reader.nextLine().toUpperCase();
                if (!RulesChecker.checkUserInput(this.authorizedColors, userInput)) {
                    this.printUserInputError();
                    i--;
                } else {
                    final String processedUserInput = RulesChecker.processMisplacedColors(this.combination, RulesChecker.processRightPlacedColors(this.combination, userInput));
                    UserTurn currentTurn = this.userTurnsList.get(i);
                    currentTurn.setProposition(userInput);

                    currentTurn.setRightPlacedColors(RulesChecker.countTokens(processedUserInput, CountTokens.RIGHTPLACED.getToken()));
                    currentTurn.setMisplacedColors(RulesChecker.countTokens(processedUserInput, CountTokens.MISPLACED.getToken()));

                    if (currentTurn.getRightPlacedColors() == 4) {
                        this.printUserTurns();
                        System.out.println("Bravo! Vous avez gagné en " + (i + 1) + " tours!");
                        i = this.maxUserTurns;
                    } else if (i == this.maxUserTurns - 1) {
                        System.out.println("Perdu! La combinaison à trouver était: " + this.combination);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            reader.close();
        }
    }
}
