package com.gamenawa.eeeeweeee.domain.games.service;

import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;

public interface IGameInfoSearcher {
    GameDto getGameInfoAndScoreByTitle(String title);
}
