package com.gamenawa.eeeeweeee.domain.games.service;

import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements IGamesService {

    private final List<IGameScoreSearcher> scoreSearchers;
    private final IGameInfoSearcher infoSearcher;

    @Override
    public GameDto getGameByTitle(String title) {
        GameDto gameDto = getGameWithInfoByTitle(title);
        if (isValidGame(gameDto)) {
            setScores(gameDto);
            return gameDto;
        }
        else {
            return null;
        }
    }

    private boolean isValidGame(GameDto gameDto) {
        return gameDto != null;
    }

    private GameDto getGameWithInfoByTitle(String title) {
        return infoSearcher.getGameInfoByTitle(title);
    }
    private void setScores(GameDto gameDto) {
        for (IGameScoreSearcher searcher : scoreSearchers) {
            gameDto.addScore(searcher.getGameScoreByTitle(gameDto.getTitle()));
        }
    }
}
