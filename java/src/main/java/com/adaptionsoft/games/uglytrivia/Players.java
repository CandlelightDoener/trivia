package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    public static final int COINS_TO_WIN = 6;
    private static final int MAX_PLAYERS = 6;

    private int currentPlayer;

    private ArrayList<Player> players = new ArrayList<Player>();

    public Players() {
    }

    public void add(Player player) {
        if(players.size() >= MAX_PLAYERS)
            throw new RuntimeException("Can't add player - maximum number of players already reached");

        players.add(player);

        System.out.println(player.getName() + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void switchToNextPlayer() {
        currentPlayer ++;
        currentPlayer %= players.size();
    }

    public String getCurrentPlayerName() {
        return players.get(currentPlayer).getName();
    }

    public void removeCurrentPlayerFromPenaltyBox(boolean shouldBeRemoved) {
        players.get(currentPlayer).removeFromPenaltyBox(shouldBeRemoved);
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
        System.out.println("The category is " + Category.getCategoryName(getCurrentPlayerLocation()));
    }

    public int getCurrentPlayerLocation() {
        return players.get(currentPlayer).getLocationOnPlayingField();
    }

    public String getCurrentPlayerCategory() {
        return Category.getCategoryName(getCurrentPlayerLocation());
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

    boolean playerHasNotEnoughCoinsYet() {
        return getCurrentPlayerCoins() != COINS_TO_WIN;
    }
}