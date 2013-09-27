package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String playerName;

    public Player(String playerName, int orderOfAppearance) {
        this.playerName = playerName;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + orderOfAppearance);
    }

    @Override
    public String toString() {
        return playerName;
    }
}
