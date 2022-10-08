package com.gamenawa.eeeeweeee.games;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameScore {
    @Builder.Default private final String score = "0";
    @Builder.Default private final String rater = "unknown";
}
