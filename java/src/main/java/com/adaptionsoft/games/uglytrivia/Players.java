package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList<Player>();

    public Players() {
    }

    public void add(Player player) {
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
}