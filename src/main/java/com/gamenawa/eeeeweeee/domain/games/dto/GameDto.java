package com.gamenawa.eeeeweeee.domain.games.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GameDto {
    private final String title;
    private final String genre;
    private final String developer;
    private final Integer releaseYear;
    private final List<GameScoreDto> scores = new ArrayList<>();

    public void addScore(GameScoreDto score) {
        this.scores.add(score);
    }
}
