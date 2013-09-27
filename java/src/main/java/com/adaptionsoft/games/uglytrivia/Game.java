package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
    public static final int NO_OF_QUESTIONS_FOR_EACH_CATEGORY = 50;
    public static final int COINS_TO_WIN = 6;
    
    final Players players = new Players();

    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    int currentPlayer = 0;

    public Game() {
        for (int i = 0; i < NO_OF_QUESTIONS_FOR_EACH_CATEGORY; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void add(String playerName) {
        players.add(new Player(playerName));
    }

    public void roll(int diceEyes) {
        System.out.println(getCorrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + diceEyes);

        if (isCorrentPlayerInPenaltyBox()) {
            if (diceEyes % 2 != 0) {
                getCorrentPlayer().removeFromPenaltyBox(true);
                move(diceEyes);
                askQuestion();
            } else {
                getCorrentPlayer().removeFromPenaltyBox(false);
            }
        } else {
            move(diceEyes);
            askQuestion();
        }
    }

    private boolean isCorrentPlayerInPenaltyBox() {
        return getCorrentPlayer().isInPenaltyBox();
    }

    private Player getCorrentPlayer() {
        return players.get(currentPlayer);
    }

    private void sendCorrentPlayerToPenaltyBox() {
        getCorrentPlayer().sendToPenaltyBox();
    }

    private void move(int diceEyes) {
        getCorrentPlayer().moveBy(diceEyes);

        System.out.println(getCorrentPlayer()
                + "'s new location is "
                + getCorrentPlayer().getLocation());
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
        return getCorrentPlayer().getCategory();
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCorrentPlayer() + " was sent to the penalty box");
        sendCorrentPlayerToPenaltyBox();

        switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        boolean keepOnPlaying = true;

        if (isCorrentPlayerInPenaltyBox()) {
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
        getCorrentPlayer().addCoin();
        System.out.println(getCorrentPlayer()
                + " now has "
                + getCorrentPlayer().getCoins()
                + " Gold Coins.");
    }


    private boolean playerHasNotEnoughCoinsYet() {
        return getCorrentPlayer().getCoins() != COINS_TO_WIN;
    }
}
