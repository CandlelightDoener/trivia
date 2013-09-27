package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    public static final int COINS_TO_WIN = 6;
    private static final int MAX_PLAYERS = 6;

    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer;

    public void addNewPlayer(String playerName) {
        if (players.size() >= MAX_PLAYERS)
            throw new RuntimeException("Can't add player - maximum number of players already reached");

        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void switchToNextPlayer() {
        currentPlayer++;
        currentPlayer %= players.size();
    }

    public String getCurrentPlayerName() {
        return players.get(currentPlayer).getName();
    }

    public void removeCurrentPlayerFromPenaltyBox(boolean shouldBeRemoved) {
        if (shouldBeRemoved)
            players.get(currentPlayer).removeFromPenaltyBox();

        System.out.println(getCurrentPlayerName() + " is " + (shouldBeRemoved ? "" : "not ") + "getting out of the penalty box");
    }

    public boolean isCurrentPlayerInPenaltyBox() {
        return players.get(currentPlayer).isInPenaltyBox();
    }

    public void sendCurrentPlayerToPenaltyBox() {
        players.get(currentPlayer).sendToPenaltyBox();

        System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
    }

    public void moveCurrentPlayerBy(int diceEyes) {
        players.get(currentPlayer).moveBy(diceEyes);

        System.out.println(getCurrentPlayerName()
                + "'s new location is "
                + getCurrentPlayerLocation());
        System.out.println("The category is " + Category.getCategory(getCurrentPlayerLocation()));
    }

    public int getCurrentPlayerLocation() {
        return players.get(currentPlayer).getLocationOnPlayingField();
    }

    public Category getCurrentPlayerCategory() {
        return Category.getCategory(getCurrentPlayerLocation());
    }

    boolean currentPlayerHasNotEnoughCoinsYet() {
        return getCurrentPlayerCoins() != COINS_TO_WIN;
    }

    public void payCurrentPlayer() {
        players.get(currentPlayer).pay();

        System.out.println(getCurrentPlayerName()
                + " now has " + getCurrentPlayerCoins()
                + " Gold Coins.");
    }

    public int getCurrentPlayerCoins() {
        return players.get(currentPlayer).getCoins();
    }

    private class Player {
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
}