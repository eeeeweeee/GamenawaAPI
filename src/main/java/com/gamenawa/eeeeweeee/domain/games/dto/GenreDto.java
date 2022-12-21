package com.gamenawa.eeeeweeee.domain.games.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class GenreDto {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;
}
