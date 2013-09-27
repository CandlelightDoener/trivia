package com.adaptionsoft.games.uglytrivia;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String categoryName;

    private Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category getCategory(int field) {
        int noOfCategories = Category.values().length;
        return Category.values()[field % noOfCategories];
    }

    @Override
    public String toString() {
        return categoryName;
    }
}