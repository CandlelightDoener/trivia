package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.players.Players;

/**
 * Things which still feel odd:
 * + all these calls to Player in Players. This happened when removing trainwrecks in the Game class.
 * + the flow in roll(int)
 * + proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying
 *   - that it returns a boolean
 *   - that it does two things
 * + Where does MAX_PLACES belong: really into player? Shouldn't it be part of some "board" or sth.?
 */
public class Game {

    final Players players = new Players();
    final CardDeck cardDeck = new CardDeck();

    public void add(String playerName) {
        players.addNewPlayer(playerName);
    }

    public void roll(int diceEyes) {
        System.out.println(players.getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + diceEyes);

        if (players.isCurrentPlayerInPenaltyBox() && isEven(diceEyes)) {
            players.removeCurrentPlayerFromPenaltyBox(false);
            return;
        }

        if (players.isCurrentPlayerInPenaltyBox()) {
            players.removeCurrentPlayerFromPenaltyBox(true);
        }

        players.moveCurrentPlayerBy(diceEyes);
        cardDeck.drawCard(players.getCurrentPlayerCategory());
    }

    private boolean isEven(int diceEyes) {
        return diceEyes % 2 == 0;
    }

    public void proceedWhenWrongAnswer() {
        System.out.println("Question was incorrectly answered");

        players.sendCurrentPlayerToPenaltyBox();
        players.switchToNextPlayer();
    }

    public boolean proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying() {
        System.out.println("Answer was correct!!!!");

        boolean keepOnPlaying = true;

        if (!players.isCurrentPlayerInPenaltyBox()) {
            players.payCurrentPlayer();
            keepOnPlaying = players.currentPlayerHasNotEnoughCoinsYet();
        }

        players.switchToNextPlayer();
        return keepOnPlaying;
    }
}