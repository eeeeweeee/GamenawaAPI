package com.gamenawa.eeeeweeee.domain.games.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class OpenCriticGameInfoAndScoreDto {

    @SerializedName("Companies")
    private List<CompanyDto> companies;

    @SerializedName("medianScore")
    private int medianScore;

    @SerializedName("Genres")
    private List<GenreDto> genres;

    @SerializedName("firstReleaseDate")
    private String releaseDate;

}
