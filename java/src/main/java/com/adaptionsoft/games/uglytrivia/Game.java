package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public static final int MAX_PLAYERS = 6;
    public static final int NO_OF_QUESTIONS_FOR_EACH_CATEGORY = 50;
    public static final int MAX_PLACES = 12;
    public static final int COINS_TO_WIN = 6;

    ArrayList<Player> players = new ArrayList<Player>();
    int[] places = new int[MAX_PLAYERS];
    int[] purses = new int[MAX_PLAYERS];
    boolean[] inPenaltyBox = new boolean[MAX_PLAYERS];

    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < NO_OF_QUESTIONS_FOR_EACH_CATEGORY; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void add(String playerName) {
        players.add(new Player(playerName, players.size() + 1)); //TODO fix that +1 later
    }

    public void roll(int diceEyes) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + diceEyes);

        if (inPenaltyBox[currentPlayer]) {
            if (diceEyes % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                move(diceEyes);
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            move(diceEyes);
            askQuestion();
        }
    }

    private void move(int diceEyes) {
        places[currentPlayer] += diceEyes;
        places[currentPlayer] %= MAX_PLACES;

        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
    }

    private void askQuestion() {
        if (currentCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0) return "Pop";
        if (places[currentPlayer] == 4) return "Pop";
        if (places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1) return "Science";
        if (places[currentPlayer] == 5) return "Science";
        if (places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2) return "Sports";
        if (places[currentPlayer] == 6) return "Sports";
        if (places[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        boolean keepOnPlaying = true;

        if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
            switchToNextPlayer();
        } else {
            givePlayerMoney();
            keepOnPlaying = playerHasNotEnoughCoinsYet();
            switchToNextPlayer();
        }

        return keepOnPlaying;
    }

    private void switchToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private void givePlayerMoney() {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
    }


    private boolean playerHasNotEnoughCoinsYet() {
        return purses[currentPlayer] != COINS_TO_WIN;
    }
}
