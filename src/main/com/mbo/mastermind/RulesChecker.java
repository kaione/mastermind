package com.mbo.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Rules checker to gather user input checking functions
 */
public class RulesChecker {
    /**
     * Checks the user input size and content via a simple regexp
     *
     * @param   input   string representing the user input
     * @return          boolean telling if the user input is valid or not
     */
    public static boolean checkUserInput(String authorizedColors, String input) {
        final Pattern pattern = Pattern.compile("^[" + authorizedColors + " ]{4}$");
        return pattern.matcher(input).matches();
    }

    /**
     * Counts the number of occurrences of the token in the string
     *
     * @param   str     string representing the fully processed user input
     * @param   token   char representing the token to count
     * @return          int representing the char count
     */
    public static int countTokens(String str, char token) {
        return (int) str.chars().filter(ch -> ch == token).count();
    }

    /**
     * Processes the user input in order to find the colors that
     * are at the right place according to the initially generated
     * combination
     *
     * @param   proposition string representing the verified user input
     * @return              string where the correctly placed colors are replaced with RIGHTPLACED tokens
     */
    public static String processRightPlacedColors(String combination, String proposition) {
        if (combination.length() != proposition.length()) {
            return "";
        }
        StringBuilder tmpProposition = new StringBuilder();

        for (int i = 0; i < proposition.length(); i++) {
            tmpProposition.append(proposition.charAt(i) == combination.charAt(i) ? CountTokens.RIGHTPLACED.getToken() : proposition.charAt(i));
        }

        return tmpProposition.toString();
    }

    /**
     * Processes the user input in order to find the colors that
     * are in the initially generated combination but not yet on the right spot
     *
     * @param   proposition string representing the verified user input previously processed right placed colors
     * @return              string where the misplaced colors are replaced with MISPLACED tokens
     */
    public static String processMisplacedColors(String combination, String proposition) {
        if (combination.length() != proposition.length()) {
            return "";
        }
        StringBuilder tmpCombination = new StringBuilder().append(combination);/* Need this to avoid counting multiple misplaced occurrences for a same color */
        StringBuilder tmpProposition = new StringBuilder();

        for (int i = 0; i < proposition.length(); i++) {
            int possibleOccurrence = tmpCombination.toString().indexOf(proposition.charAt(i));
            if (possibleOccurrence != -1 && proposition.charAt(possibleOccurrence) != CountTokens.RIGHTPLACED.getToken()) {
                tmpProposition.append(CountTokens.MISPLACED.getToken());
                tmpCombination.setCharAt(possibleOccurrence, 'X');
            } else {
                tmpProposition.append(proposition.charAt(i));
            }
        }

        return tmpProposition.toString();
    }
}
