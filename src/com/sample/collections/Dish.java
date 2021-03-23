package com.sample.collections;

public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public int getCalories() {
        return calories;
    }
    public Type getType() {
        return type;
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        sb.append(getName());sb.append("|");
        sb.append(((isVegetarian())?"Vegetarian":"Omnivorous"));sb.append("|");
        sb.append(getCalories());sb.append("|");
        sb.append(getType().name());
        sb.append("]");
        return sb.toString();
    }
    public enum Type { MEAT, FISH, OTHER }
}