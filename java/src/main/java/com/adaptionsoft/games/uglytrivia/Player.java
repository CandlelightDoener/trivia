package com.adaptionsoft.games.uglytrivia;

public class Player {
    private static final int MAX_PLACES = 12;

    private final String playerName;
    private boolean inPenaltyBox;
    private int coins;
    private int locationOnPlayingField;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true;
    }

    public void pay() {
        coins++;
    }

    public int getCoins() {
        return coins;
    }

    public void removeFromPenaltyBox(boolean shouldBeRemoved) {
        String maybe = "";
        if(shouldBeRemoved) {
            inPenaltyBox = false;
        } else {
            maybe = "not ";
        }

        System.out.println(playerName + " is " + maybe + "getting out of the penalty box");
    }

    public void moveBy(int diceEyes) {
        locationOnPlayingField += diceEyes;
        locationOnPlayingField %= MAX_PLACES;
    }

    public int getLocationOnPlayingField() {
        return locationOnPlayingField;
    }

    public String getName() {
        return playerName;
    }
}