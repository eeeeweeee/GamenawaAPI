package com.gamenawa.eeeeweeee.domain.games;

import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;
import com.gamenawa.eeeeweeee.domain.games.dto.GameScoreDto;
import com.gamenawa.eeeeweeee.domain.games.service.GamesServiceImpl;
import com.gamenawa.eeeeweeee.domain.games.service.IGameInfoSearcher;
import com.gamenawa.eeeeweeee.domain.games.service.IGameScoreSearcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void getGameByTitleWhenValidTitle() {
        // given
        String title = "ValidTitle";
        GameDto validGameDto = new GameDto(title, "ValidGenre", "ValidDev", 1996);
        when(gameInfoSearcher.getGameInfoByTitle(title)).thenReturn(validGameDto);
        GameScoreDto gameScoreDtoOne = GameScoreDto.builder().score("40").rater("hate").build();
        GameScoreDto gameScoreDtoTwo = GameScoreDto.builder().score("80").rater("like").build();
        when(scoreSearcherOne.getGameScoreByTitle(title)).thenReturn(gameScoreDtoOne);
        when(scoreSearcherTwo.getGameScoreByTitle(title)).thenReturn(gameScoreDtoTwo);
        gameScoreSearchers.add(scoreSearcherOne);
        gameScoreSearchers.add(scoreSearcherTwo);
        gamesService = new GamesServiceImpl(gameScoreSearchers, gameInfoSearcher);

        // when
        GameDto result = gamesService.getGameByTitle(title);

        // then
        assertThat(result).isEqualTo(validGameDto);
    }
    @Test
    void getGameByTitleWhenInvalidTitle() {
        // given
        String title = "InvalidTitle";
        when(gameInfoSearcher.getGameInfoByTitle(title)).thenReturn(null);
        gamesService = new GamesServiceImpl(gameScoreSearchers, gameInfoSearcher);

        // when, then
        assertThatThrownBy(() -> gamesService.getGameByTitle(title)).isInstanceOf(ResponseStatusException.class);
    }
}