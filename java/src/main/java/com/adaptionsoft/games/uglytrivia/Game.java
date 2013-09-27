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
        System.out.println(players.getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + diceEyes);

        if (isCurrentPlayerInPenaltyBox()) {
            if (diceEyes % 2 != 0) {
                players.removeCurrentPlayerFromPenaltyBox(true);
                move(diceEyes);
                askQuestion();
            } else {
                players.removeCurrentPlayerFromPenaltyBox(false);
            }
        } else {
            move(diceEyes);
            askQuestion();
        }
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return players.isCurrentPlayerInPenaltyBox();
    }

    private void sendCurrentPlayerToPenaltyBox() {
        players.sendCurrentPlayerToPenaltyBox();
    }

    private void move(int diceEyes) {
        players.moveCurrentPlayerBy(diceEyes);

        System.out.println(players.getCurrentPlayerName()
                + "'s new location is "
                + players.getCurrentPlayerLocation());
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
        return players.getCurrentPlayerCategory();
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getCurrentPlayerName() + " was sent to the penalty box");
        sendCurrentPlayerToPenaltyBox();

        players.switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        boolean keepOnPlaying = true;

        if (isCurrentPlayerInPenaltyBox()) {
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
        players.payCurrentPlayer();
        System.out.println(players.getCurrentPlayerName()
                + " now has " + players.getCurrentPlayerCoins()
                + " Gold Coins.");
    }


    private boolean playerHasNotEnoughCoinsYet() {
        return players.getCurrentPlayerCoins() != COINS_TO_WIN;
    }
}
