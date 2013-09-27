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
        System.out.println(players.getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + diceEyes);

        if (isCorrentPlayerInPenaltyBox()) {
            if (diceEyes % 2 != 0) {
                players.getCurrentPlayer().removeFromPenaltyBox(true);
                move(diceEyes);
                askQuestion();
            } else {
                players.getCurrentPlayer().removeFromPenaltyBox(false);
            }
        } else {
            move(diceEyes);
            askQuestion();
        }
    }

    private boolean isCorrentPlayerInPenaltyBox() {
        return players.getCurrentPlayer().isInPenaltyBox();
    }

    private void sendCorrentPlayerToPenaltyBox() {
        players.getCurrentPlayer().sendToPenaltyBox();
    }

    private void move(int diceEyes) {
        players.getCurrentPlayer().moveBy(diceEyes);

        System.out.println(players.getCurrentPlayer()
                + "'s new location is "
                + players.getCurrentPlayer().getLocation());
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
        return players.getCurrentPlayer().getCategory();
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getCurrentPlayer() + " was sent to the penalty box");
        sendCorrentPlayerToPenaltyBox();

        players.switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        boolean keepOnPlaying = true;

        if (isCorrentPlayerInPenaltyBox()) {
            players.switchToNextPlayer();
        } else {
            givePlayerMoney();
            keepOnPlaying = playerHasNotEnoughCoinsYet();
            players.switchToNextPlayer();
        }

        return keepOnPlaying;
    }

    private void givePlayerMoney() {
        System.out.println("Answer was correct!!!!");
        players.getCurrentPlayer().addCoin();
        System.out.println(players.getCurrentPlayer()
                + " now has "
                + players.getCurrentPlayer().getCoins()
                + " Gold Coins.");
    }


    private boolean playerHasNotEnoughCoinsYet() {
        return players.getCurrentPlayer().getCoins() != COINS_TO_WIN;
    }
}
