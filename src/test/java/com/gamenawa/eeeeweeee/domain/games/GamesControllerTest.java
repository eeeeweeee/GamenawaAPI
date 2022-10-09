package com.gamenawa.eeeeweeee.domain.games;

import com.gamenawa.eeeeweeee.domain.games.controller.GamesController;
import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;
import com.gamenawa.eeeeweeee.domain.games.service.IGamesService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GamesControllerTest {
    @InjectMocks
    GamesController gamesController;
    @Mock
    IGamesService gamesService;
    String validGameTitle = "ValidGameTitle";
    String invalidGameTitle = "InvalidGameTitle";
    GameDto validResult = mock(GameDto.class);

    @BeforeEach
    public void beforeEach() {
        gamesService = mock(IGamesService.class); // 왜인지 이걸 안넣으면 안돌아감
    }

    @Test
    void getGameByValidTitle() {
        // given
        when(gamesService.getGameByTitle(validGameTitle)).thenReturn(validResult);
        gamesController = new GamesController(gamesService);

        // then
        Assertions.assertThat(gamesController.getGame(validGameTitle, mock(HttpServletResponse.class)))
                .isEqualTo(validResult);
    }

    @Test
    void getGameByInvalidTitle() {
        // given
        when(gamesService.getGameByTitle(invalidGameTitle)).thenReturn(null);
        gamesController = new GamesController(gamesService);
        HttpServletResponse response = new MockHttpServletResponse();

        // when
        gamesController.getGame(invalidGameTitle, response);

        // then
        Assertions.assertThat(response.getStatus())
                .isEqualTo(HttpServletResponse.SC_NOT_FOUND);
    }
}