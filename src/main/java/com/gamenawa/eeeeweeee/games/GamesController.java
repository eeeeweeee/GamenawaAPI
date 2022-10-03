package com.gamenawa.eeeeweeee.games;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class GamesController {
    private final IGamesService gamesService;

    @GetMapping("/games")
    @ResponseBody
    public String getGame(@RequestParam String title, HttpServletRequest request, HttpServletResponse response) {
        String json = gamesService.getGameByTitle(title);
        if (json == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return json;
    }
}
