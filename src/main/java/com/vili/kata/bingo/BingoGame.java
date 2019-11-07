package com.vili.kata.bingo;

import reactor.core.publisher.Flux;

import java.util.List;

import static com.vili.kata.bingo.Card.NO_PRIZE_CARD_YET;

public class BingoGame {

    private BallsGenerator ballsGenerator;
    private Balls balls;
    private CardVerifier verifier;
    private List<Card> cards;

    public BingoGame(BallsGenerator ballsGenerator, Balls balls, List<Card> cards) {
        this.ballsGenerator = ballsGenerator;
        this.balls = balls;
        this.cards = cards;
    }

    public Flux<Card> playGame() {
        return ballsGenerator.generate()
                .map(balls::createBallWithNumber)
                .map(ball -> verifier.verify(cards, ball))
                .onErrorResume(RuntimeException.class, e -> Flux.just(NO_PRIZE_CARD_YET));
    }

}
