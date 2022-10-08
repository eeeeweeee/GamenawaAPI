package com.gamenawa.eeeeweeee.domain.games.service;

import com.gamenawa.eeeeweeee.domain.games.dto.Game;

public interface IGameInfoSearcher {
    Game getGameInfoByTitle(String title);
}
