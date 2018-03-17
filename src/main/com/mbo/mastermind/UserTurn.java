package com.mbo.mastermind;

/**
 * Helper to store and print user turns easily
 */
public class UserTurn {
    private String proposition = "....";
    private String turnTracker = "0/0";
    private int misplacedColors = -1;
    private int rightPlacedColors = -1;
    private boolean turnPlayed = false;

    /**
     * Helper to store and print user turns easily
     * @param   turnNb      int representing the turn for which this UserTun will be used
     * @param   maxTurns    int representing the maximum number of the turns for the game
     */
    UserTurn(int turnNb, int maxTurns) {
        this.proposition = proposition;
        this.turnTracker = turnNb + "/" + maxTurns;
    }

    /**
     * Helper to store and print user turns easily
     * @return  string representing the formatted user turn row for console logging
     */
    public String turnToString() {
        return "|" + this.proposition
                + "| " + (this.misplacedColors != -1 ? this.misplacedColors : ".")
                + " | " + (this.rightPlacedColors != -1 ? this.rightPlacedColors : ".")
                + (this.turnTracker.length() == 4 ? " | " : " |") + this.turnTracker
                + " |";
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
        this.turnPlayed = true;
    }

    public void setMisplacedColors(int misplacedColors) {
        this.misplacedColors = misplacedColors;
    }

    public void setRightPlacedColors(int rightPlacedColors) {
        this.rightPlacedColors = rightPlacedColors;
    }

    public int getRightPlacedColors() {
        return this.rightPlacedColors;
    }

    public boolean isPlayed() {
        return this.turnPlayed;
    }
}
