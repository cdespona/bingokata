package com.vili.kata.bingo;

public class Cell {
    public final int number;
    private boolean found;

    public Cell(int number) {
        this.number = number;
        this.found = false;
    }

    public void found(boolean found) {
        this.found = found;
    }

    public boolean found() {
        return this.found;
    }
}
