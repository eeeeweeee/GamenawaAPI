package com.gamenawa.eeeeweeee.domain.games.service;

import com.gamenawa.eeeeweeee.domain.games.dto.GameScoreDto;

public interface IGameScoreSearcher {
    GameScoreDto getGameScoreByTitle(String title);
}
