package com.adaptionsoft.games.uglytrivia.players;

class Player {
    private static final int MAX_PLACES = 12;

    private final String playerName;
    private boolean inPenaltyBox;
    private int coins;
    private int locationOnPlayingField;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getName() {
        return playerName;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true;
    }

    public void removeFromPenaltyBox() {
        inPenaltyBox = false;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void pay() {
        coins++;
    }

    public int getCoins() {
        return coins;
    }

    public void moveBy(int diceEyes) {
        locationOnPlayingField += diceEyes;
        locationOnPlayingField %= MAX_PLACES;
    }

    public int getLocationOnPlayingField() {
        return locationOnPlayingField;
    }
}
