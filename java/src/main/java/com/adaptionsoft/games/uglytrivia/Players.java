package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private static final int MAX_PLAYERS = 6;

    private int currentPlayer;

    ArrayList<Player> players = new ArrayList<Player>();

    public Players() {
    }

    public void add(Player player) {
        if(players.size() >= MAX_PLAYERS)
            throw new RuntimeException("Can't add player - maximum number of players already reached");

        players.add(player);

        System.out.println(player + " was added");
        System.out.println("They are player number " + players.size());
    }

    public Player get(int currentPlayer) {
        return players.get(currentPlayer);
    }

    public int size() {
        return players.size();
    }

    public void switchToNextPlayer() {
        currentPlayer ++;
        currentPlayer %= players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}