package com.vili.kata.bingo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

public class BingoGameTest {

    private BingoGame bingoGame;
    private List<Card> cards = new ArrayList<>();

    @Mock
    private BallsGenerator ballsGenerator;
    @Mock
    private Balls balls;

    private Ball ball1;
    private Ball ball2;
    private Ball ball3;
    private Ball ball4;
    private Ball ball5;
    private Ball ball6;
    private Ball ball7;
    private Ball ball8;
    private Ball ball9;
    private Card card;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        BingoNumber one = new BingoNumber(1);
        ball1 = new Ball(one, Prize.NO_PRIZE, null);
        BingoNumber two = new BingoNumber(2);
        ball2 = new Ball(two, Prize.NO_PRIZE, null);
        BingoNumber three = new BingoNumber(3);
        ball3 = new Ball(three, Prize.NO_PRIZE, null);
        BingoNumber four = new BingoNumber(4);
        ball4 = new Ball(four, Prize.LINE, null);
        BingoNumber five = new BingoNumber(5);
        ball5 = new Ball(five, Prize.NO_PRIZE, null);
        BingoNumber six = new BingoNumber(6);
        ball6 = new Ball(six, Prize.NO_PRIZE, null);
        BingoNumber seven = new BingoNumber(7);
        ball7 = new Ball(seven, Prize.NO_PRIZE, null);
        BingoNumber eight = new BingoNumber(8);
        ball8 = new Ball(eight, Prize.NO_PRIZE, null);
        BingoNumber nine = new BingoNumber(9);
        ball9 = new Ball(nine, Prize.BINGO, null);

        Cell[][] structure = new Cell[2][3];
        structure[0][0] = new Cell(1);
        structure[0][1] = new Cell(2);
        structure[0][2] = new Cell(4);
        structure[1][0] = new Cell(5);
        structure[1][1] = new Cell(6);
        structure[1][2] = new Cell(9);

        card = new Card(structure);
        cards.add(card);
        bingoGame = new BingoGame(ballsGenerator, balls, cards);

        Flux<BingoNumber> ballsGenerated = Flux
                .just(one, two, three, four, five, six, seven, eight, nine)
                .delayElements(Duration.ofSeconds(2));
        given(ballsGenerator.generate()).willReturn(ballsGenerated);
        given(balls.createBallWithNumber(one)).willReturn(ball1);
        given(balls.createBallWithNumber(two)).willReturn(ball2);
        given(balls.createBallWithNumber(three)).willReturn(ball3);
        given(balls.createBallWithNumber(four)).willReturn(ball4);
        given(balls.createBallWithNumber(five)).willReturn(ball5);
        given(balls.createBallWithNumber(six)).willReturn(ball6);
        given(balls.createBallWithNumber(seven)).willReturn(ball7);
        given(balls.createBallWithNumber(eight)).willReturn(ball8);
        given(balls.createBallWithNumber(nine)).willReturn(ball9);
    }

    @Test
    void play_bingo_game_with_9_balls() {
        StepVerifier.create(bingoGame.playGame())
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(Card.NO_PRIZE_CARD_YET)
                .expectNext(card)
                .verifyComplete();
    }
}
