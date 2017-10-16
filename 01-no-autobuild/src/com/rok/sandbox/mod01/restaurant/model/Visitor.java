package com.rok.sandbox.mod01.restaurant.model;

public class Visitor {
    private String name;
    private int maximumTablesToBook;

    public Visitor(String name) {
        setName(name);
        maximumTablesToBook = 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "NoName" : name;
    }

    public int getMaximumTablesToBook() {
        return maximumTablesToBook;
    }

    public void setMaximumTablesToBook(int maximumTablesToBook) {
        this.maximumTablesToBook = maximumTablesToBook;
    }

    public String toString() {
        return this.getName() + " (max tables he can book: " + this.getMaximumTablesToBook()+")";
    }
}
