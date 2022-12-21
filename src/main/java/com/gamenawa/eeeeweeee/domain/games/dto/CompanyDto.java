package com.gamenawa.eeeeweeee.domain.games.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CompanyDto {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;
}
