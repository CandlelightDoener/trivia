package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
