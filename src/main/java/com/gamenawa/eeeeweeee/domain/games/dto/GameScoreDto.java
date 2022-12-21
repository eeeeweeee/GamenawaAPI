package com.gamenawa.eeeeweeee.domain.games.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameScoreDto {
    @Builder.Default private final int score = 0;
    @Builder.Default private final String rater = "unknown";
}
