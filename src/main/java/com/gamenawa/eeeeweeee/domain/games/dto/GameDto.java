package com.gamenawa.eeeeweeee.domain.games.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameDto {
    private final String title;
    private final List<String> genre;
    private final String developer;
    private final int releaseYear;
    private final List<GameScoreDto> scores = new ArrayList<>();

    public void addScore(GameScoreDto score) {
        this.scores.add(score);
    }

    public GameDto(String title, List<String> genre, String developer, String releaseDate) {
        this.title = title;
        this.genre = genre;
        this.developer = developer;
        this.releaseYear = Integer.parseInt(releaseDate.substring(0, 4));
    }
}
