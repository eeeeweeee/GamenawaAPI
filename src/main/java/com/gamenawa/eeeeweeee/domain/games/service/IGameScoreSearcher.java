package com.gamenawa.eeeeweeee.domain.games.service;

import com.gamenawa.eeeeweeee.domain.games.dto.GameScore;

public interface IGameScoreSearcher {
    GameScore getGameScoreByTitle(String title);
}
