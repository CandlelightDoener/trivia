package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {

    public static final int NO_OF_QUESTIONS_FOR_EACH_CATEGORY = 50;
    
    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    public Questions() {
        for (int i = 0; i < NO_OF_QUESTIONS_FOR_EACH_CATEGORY; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }


    public void ask(String currentPlayerCategory) {
        if (currentPlayerCategory.equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentPlayerCategory.equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentPlayerCategory.equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentPlayerCategory.equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }
}
