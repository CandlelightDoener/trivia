package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String playerName;
    private boolean inPenaltyBox;
    private int coins;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true; //TODO: exposed bug -> a player apparently never gets out of the penalty box
    }

    public void addCoin() {
        coins++;
    }

    public int getCoins() {
        return coins;
    }

    public void removeFromPenaltyBox(boolean shouldBeRemoved) {
        String maybe = "";
        if(shouldBeRemoved) {
            //inPenaltyBox = false;
        } else {
            maybe = "not ";
        }

        System.out.println(playerName + " is " + maybe + "getting out of the penalty box");
    }
}
