package com.gamenawa.eeeeweeee.domain.games.controller;

import com.gamenawa.eeeeweeee.domain.games.dto.GameDto;
import com.gamenawa.eeeeweeee.domain.games.service.IGamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class GamesController {
    private final IGamesService gamesService;

    @GetMapping("/games")
    public GameDto getGame(@RequestParam String title, HttpServletResponse response) {
        try {
            return gamesService.getGameByTitle(title);
        }
        catch (ResponseStatusException e) {
            response.setStatus(e.getStatus().value());
            return null;
        }
    }
}
