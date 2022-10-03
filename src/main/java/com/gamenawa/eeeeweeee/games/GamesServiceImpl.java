package com.gamenawa.eeeeweeee.games;

import com.gamenawa.eeeeweeee.util.IJsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements IGamesService {

    private final List<IGameScoreSearcher> scoreSearchers;
    private final IGameInfoSearcher infoSearcher;
    private final IJsonParser jsonParser;

    @Override
    public String getGameByTitle(String title) {
        Game game = getGameWithInfoByTitle(title);
        if (isValidGame(game)) {
            setScores(game);
            return jsonParser.toJson(game);
        }
        else {
            return null;
        }
    }

    private boolean isValidGame(Game game) {
        return game != null;
    }

    private Game getGameWithInfoByTitle(String title) {
        return infoSearcher.getGameInfoByTitle(title);
    }
    private void setScores(Game game) {
        for (IGameScoreSearcher searcher : scoreSearchers) {
            game.addScore(searcher.getGameScoreByTitle(game.getTitle()));
        }
    }
}
