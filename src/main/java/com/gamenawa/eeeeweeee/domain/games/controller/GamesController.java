package com.gamenawa.eeeeweeee.domain.games.controller;

import com.gamenawa.eeeeweeee.domain.games.dto.Game;
import com.gamenawa.eeeeweeee.domain.games.service.IGamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class GamesController {
    private final IGamesService gamesService;

    @GetMapping("/games")
    public Game getGame(@RequestParam String title, HttpServletRequest request, HttpServletResponse response) {
        Game game = gamesService.getGameByTitle(title);
        if (game == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return game;
    }
}
