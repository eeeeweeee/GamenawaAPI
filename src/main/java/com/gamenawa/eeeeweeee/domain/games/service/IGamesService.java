package com.gamenawa.eeeeweeee.domain.games.service;

import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;

public interface IGamesService {
    GameDto getGameByTitle(String title);
}
