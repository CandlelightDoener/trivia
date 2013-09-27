package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.Map;

public class Questions {

    private Map<Category, Counter> questions = new HashMap<Category, Counter>();

    public Questions() {
        for (Category category : Category.values()) {
            questions.put(category, new Counter());
        }
    }

    public void ask(Category currentPlayerCategory) {
        Counter counter = questions.get(currentPlayerCategory);

        System.out.println(currentPlayerCategory.getCategoryName() + " Question " + counter.value);

        counter.raise();
    }

    private class Counter {
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
