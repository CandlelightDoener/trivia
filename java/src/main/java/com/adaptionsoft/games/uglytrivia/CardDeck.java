package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.Map;

public class CardDeck {

    private Map<Category, DrawnCardsCounter> questions = new HashMap<Category, DrawnCardsCounter>();

    public CardDeck() {
        for (Category category : Category.values()) {
            questions.put(category, new DrawnCardsCounter());
        }
    }

    public void drawCard(Category currentPlayerCategory) {
        DrawnCardsCounter drawnCards = questions.get(currentPlayerCategory);

        System.out.println(currentPlayerCategory + " Question " + drawnCards.value);

        drawnCards.raise();
    }

    private class DrawnCardsCounter {
        private static final int NO_OF_QUESTIONS_FOR_EACH_CATEGORY = 50;

        int value = 0;

        void raise() {
            if (value >= NO_OF_QUESTIONS_FOR_EACH_CATEGORY) {
                throw new RuntimeException("Deck is depleted");
            }
            value++;
        }
    }
}