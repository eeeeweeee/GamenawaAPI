package com.gamenawa.eeeeweeee.games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private Game game;

    @BeforeEach
    public void beforeEach() {
        game = new Game("testTitle", "testGenre", "testDev", 1996);
    }

    @Test
    void addScore() {
        game.addScore(GameScore.builder().score("40").rater("hate").build());
        game.addScore(GameScore.builder().score("80").rater("like").build());
        assertThat(game.getScores().get(0).getScore()).isEqualTo("40");
        assertThat(game.getScores().get(0).getRater()).isEqualTo("hate");
        assertThat(game.getScores().get(1).getScore()).isEqualTo("80");
        assertThat(game.getScores().get(1).getRater()).isEqualTo("like");
    }

    @Test
    void getTitle() {
        assertThat(game.getTitle()).isEqualTo("testTitle");
    }

    @Test
    void getGenre() {
        assertThat(game.getGenre()).isEqualTo("testGenre");
    }

    @Test
    void getDeveloper() {
        assertThat(game.getDeveloper()).isEqualTo("testDev");
    }

    @Test
    void getReleaseYear() {
        assertThat(game.getReleaseYear()).isEqualTo(1996);
    }

    @Test
    void getScores() {
        game.addScore(GameScore.builder().score("40").rater("hate").build());
        game.addScore(GameScore.builder().score("80").rater("like").build());
        assertThat(game.getScores().size()).isEqualTo(2);
    }
}