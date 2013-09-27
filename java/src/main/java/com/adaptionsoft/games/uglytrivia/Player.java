package com.adaptionsoft.games.uglytrivia;

public class Player {
    private static final int MAX_PLACES = 12;

    private final String playerName;
    private boolean inPenaltyBox;
    private int coins;
    private int places;

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
        inPenaltyBox = true;
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
            inPenaltyBox = false;
        } else {
            maybe = "not ";
        }

        System.out.println(playerName + " is " + maybe + "getting out of the penalty box");
    }

    public void moveBy(int diceEyes) {
        places += diceEyes;
        places %= MAX_PLACES;
    }

    public int getLocation() {
        return places;
    }

    public String getCategory() {
        if (places == 0) return "Pop";
        if (places == 4) return "Pop";
        if (places == 8) return "Pop";
        if (places == 1) return "Science";
        if (places == 5) return "Science";
        if (places == 9) return "Science";
        if (places == 2) return "Sports";
        if (places == 6) return "Sports";
        if (places == 10) return "Sports";
        return "Rock";    }
}
