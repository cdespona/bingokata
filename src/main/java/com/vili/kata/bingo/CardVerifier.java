package com.vili.kata.bingo;

import java.util.List;

public class CardVerifier {

    public Card verify (List<Card> cards, Ball ball) {
        return cards.stream()
                .map(card -> card.check(ball))
                .filter(Card::shoutLine)
                .findFirst()
                .orElse(Card.NO_PRIZE_CARD_YET);
    }

}
