package com.gamenawa.eeeeweeee.domain.games;

import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;
import com.gamenawa.eeeeweeee.domain.games.dto.GameScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameDtoTest {
    private GameDto gameDto;

    @BeforeEach
    public void beforeEach() {
        gameDto = new GameDto("testTitle", "testGenre", "testDev", 1996);
    }

    @Test
    void addScore() {
        gameDto.addScore(GameScoreDto.builder().score("40").rater("hate").build());
        gameDto.addScore(GameScoreDto.builder().score("80").rater("like").build());
        assertThat(gameDto.getScores().get(0).getScore()).isEqualTo("40");
        assertThat(gameDto.getScores().get(0).getRater()).isEqualTo("hate");
        assertThat(gameDto.getScores().get(1).getScore()).isEqualTo("80");
        assertThat(gameDto.getScores().get(1).getRater()).isEqualTo("like");
    }

    @Test
    void getTitle() {
        assertThat(gameDto.getTitle()).isEqualTo("testTitle");
    }

    @Test
    void getGenre() {
        assertThat(gameDto.getGenre()).isEqualTo("testGenre");
    }

    @Test
    void getDeveloper() {
        assertThat(gameDto.getDeveloper()).isEqualTo("testDev");
    }

    @Test
    void getReleaseYear() {
        assertThat(gameDto.getReleaseYear()).isEqualTo(1996);
    }

    @Test
    void getScores() {
        gameDto.addScore(GameScoreDto.builder().score("40").rater("hate").build());
        gameDto.addScore(GameScoreDto.builder().score("80").rater("like").build());
        assertThat(gameDto.getScores().size()).isEqualTo(2);
    }
}