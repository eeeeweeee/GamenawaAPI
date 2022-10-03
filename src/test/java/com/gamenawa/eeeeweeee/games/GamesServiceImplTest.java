package com.gamenawa.eeeeweeee.games;

import com.gamenawa.eeeeweeee.util.GsonParser;
import com.gamenawa.eeeeweeee.util.IJsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamesServiceImplTest {
    GamesServiceImpl gamesService;
    @Mock
    IGameInfoSearcher gameInfoSearcher;

    List<IGameScoreSearcher> gameScoreSearchers = new ArrayList<>();

    @Mock
    IGameScoreSearcher scoreSearcherOne;

    @Mock
    IGameScoreSearcher scoreSearcherTwo;

    @Spy
    IJsonParser parser = new GsonParser();

    @Test
    void getGameByTitleWhenValidTitle() {
        // given
        String title = "ValidTitle";
        Game validGame = new Game(title, "ValidGenre", "ValidDev", 1996);
        when(gameInfoSearcher.getGameInfoByTitle(title)).thenReturn(validGame);
        GameScore gameScoreOne = new GameScore("40", "hate");
        GameScore gameScoreTwo = new GameScore("80", "like");
        when(scoreSearcherOne.getGameScoreByTitle(title)).thenReturn(gameScoreOne);
        when(scoreSearcherTwo.getGameScoreByTitle(title)).thenReturn(gameScoreTwo);
        gameScoreSearchers.add(scoreSearcherOne);
        gameScoreSearchers.add(scoreSearcherTwo);
        gamesService = new GamesServiceImpl(gameScoreSearchers, gameInfoSearcher, parser);

        // when
        String result = gamesService.getGameByTitle(title);

        // then
        Assertions.assertThat(result).isEqualTo("{" +
                "\"title\":\"ValidTitle\"," +
                "\"genre\":\"ValidGenre\"," +
                "\"developer\":\"ValidDev\"," +
                "\"releaseYear\":1996," +
                "\"scores\":" +
                "[" +
                    "{\"score\":\"40\"," +
                    "\"rater\":\"hate\"}," +
                    "{\"score\":\"80\"," +
                    "\"rater\":\"like\"}" +
                "]" +
                "}");
    }
    @Test
    void getGameByTitleWhenInvalidTitle() {
        // given
        String title = "InvalidTitle";
        when(gameInfoSearcher.getGameInfoByTitle(title)).thenReturn(null);
        gamesService = new GamesServiceImpl(gameScoreSearchers, gameInfoSearcher, parser);

        // when
        String result = gamesService.getGameByTitle(title);

        // then
        Assertions.assertThat(result).isEqualTo(null);
    }
}