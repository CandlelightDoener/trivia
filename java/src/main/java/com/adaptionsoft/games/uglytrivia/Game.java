package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
    public static final int NO_OF_QUESTIONS_FOR_EACH_CATEGORY = 50;

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

        if (players.isCurrentPlayerInPenaltyBox()) {
            if (diceEyes % 2 != 0) {
                players.removeCurrentPlayerFromPenaltyBox(true);
                players.moveCurrentPlayerBy(diceEyes);
                askQuestion();
            } else {
                players.removeCurrentPlayerFromPenaltyBox(false);
            }
        } else {
            players.moveCurrentPlayerBy(diceEyes);
            askQuestion();
        }
    }

    private void askQuestion() {
        if (players.getCurrentPlayerCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (players.getCurrentPlayerCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (players.getCurrentPlayerCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (players.getCurrentPlayerCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");

        players.sendCurrentPlayerToPenaltyBox();
        players.switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        boolean keepOnPlaying = true;

        if (players.isCurrentPlayerInPenaltyBox()) {
            players.switchToNextPlayer();
        } else {
            givePlayerMoney();
            keepOnPlaying = players.playerHasNotEnoughCoinsYet();
            players.switchToNextPlayer();
        }

        return keepOnPlaying;
    }

    private void givePlayerMoney() {
        System.out.println("Answer was correct!!!!");
        players.payCurrentPlayer();
    }
}