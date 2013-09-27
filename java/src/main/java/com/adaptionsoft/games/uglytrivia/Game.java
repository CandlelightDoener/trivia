package com.adaptionsoft.games.uglytrivia;

public class Game {

    final Players players = new Players();
    final Questions questions = new Questions();

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
        questions.ask(players.getCurrentPlayerCategory());
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");

        players.sendCurrentPlayerToPenaltyBox();
        players.switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        System.out.println("Answer was correct!!!!");

        boolean keepOnPlaying = true;

        if (! players.isCurrentPlayerInPenaltyBox()) {
            players.payCurrentPlayer();
            keepOnPlaying = players.playerHasNotEnoughCoinsYet();
        }

        players.switchToNextPlayer();
        return keepOnPlaying;
    }

}