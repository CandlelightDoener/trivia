
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

    private static boolean notAWinnerYet;

    public static void main(String[] args) {

        int noOfRuns = 1;
        if (args.length >= 1)
            noOfRuns = Integer.parseInt(args[0]);

        Random rand = new Random(12345);

        for (int i = 0; i < noOfRuns; i++) {

            Game aGame = new Game();

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");


            do {

                aGame.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    aGame.proceedWhenWrongAnswer();
                    notAWinnerYet = true;
                } else {
                    notAWinnerYet = aGame.proceedWhenCorrectlyAnswered_andDetermineIfWeShouldKeepOnPlaying();
                }


            } while (notAWinnerYet);

        }

    }
}
