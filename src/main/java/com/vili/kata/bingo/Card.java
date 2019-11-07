package com.vili.kata.bingo;

import java.util.Arrays;

public class Card {
    private Cell[][] structure;
    private boolean hasLine;
    private boolean hasBingo;

    public static final Card NO_PRIZE_CARD_YET = new Card(null);

    public Card(Cell[][] structure) {
        this.structure = structure;
    }

    public Card check(Ball ball) {
        for (int x = 0; x < structure.length; x++) {
            for (int y = 0; y < structure[x].length; y++) {
                if (structure[x][y].number == ball.getNumber()) {
                    structure[x][y].found(true);
                    hasLine = hasLine && structure[x][y].found();
                }
            }
            hasBingo = hasBingo && hasLine;
        }
        return this;
    }


    public static boolean shoutLine(Card card) {
        return card.hasLine;
    }

    public static boolean shoutLineOrBingo(Card card) {
        return card.hasLine || card.hasBingo;
    }

    /*public static int BingoIfPossible(Card card, Card card1) {
        if (card.hasLine == card1.hasLine && card.hasBingo == card1.hasBingo ) {
            return 0;
        } else if ((card.hasLine && !card.hasBingo) && (!card1.hasLine && !card1.hasBingo))
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(hasLine, card.hasLine)
                .append(hasBingo, card.hasBingo)
                .append(structure, card.structure)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(structure)
                .append(hasLine)
                .append(hasBingo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Card{" +
                "structure=" + Arrays.toString(structure) +
                ", hasLine=" + hasLine +
                ", hasBingo=" + hasBingo +
                '}';
    }
}
