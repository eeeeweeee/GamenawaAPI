package com.gamenawa.eeeeweeee.domain.games.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gamenawa.eeeeweeee.domain.games.dto.*;
import com.gamenawa.eeeeweeee.global.util.json.ApiCall;
import com.gamenawa.eeeeweeee.global.util.json.GsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OpenCriticGameInfoSearcher implements IGameInfoSearcher{

    private final ApiCall apiCall;
    
    private final GsonParser gsonParser;

    // TODO: 게임을 못찾아올 때 최대한 비슷한 게임이라도 찾아올것인지? 이름이 다르면 아예 안 보여줄 것인지?
    private int getGameIdByTitle(String title) throws JsonProcessingException {
        ResponseEntity<String> response = apiCall.getGameInfoFromOpenCritic(title);
        List<OpenCriticGameDto> openCriticGameDtoList = Arrays.asList(gsonParser.fromJson(response.getBody(), OpenCriticGameDto[].class));

        return openCriticGameDtoList.get(0).getId();
    }

    @Override
    public GameDto getGameInfoAndScoreByTitle(String title) {
        try {
            int gameId = this.getGameIdByTitle(title);
            ResponseEntity<String> response = apiCall.getGameScoreFromOpenCritic(gameId);
            OpenCriticGameInfoAndScoreDto openCriticGameInfoAndScoreDto = gsonParser.fromJson(response.getBody(), OpenCriticGameInfoAndScoreDto.class);
            List<String> genres = new ArrayList<>();
            for(GenreDto genre: openCriticGameInfoAndScoreDto.getGenres()) {
                genres.add(genre.getName());
            }
            GameScoreDto gameScoreDto = GameScoreDto.builder().score(openCriticGameInfoAndScoreDto.getMedianScore()).rater("OpenCritic").build();
            GameDto gameDto = new GameDto(title, genres, openCriticGameInfoAndScoreDto.getCompanies().get(0).getName(), openCriticGameInfoAndScoreDto.getReleaseDate());
            gameDto.addScore(gameScoreDto);

            return gameDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
