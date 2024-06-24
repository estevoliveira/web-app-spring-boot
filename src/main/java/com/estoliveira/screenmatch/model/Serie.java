package com.estoliveira.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(
        @JsonAlias("Title")String titulo,
        @JsonAlias("Plot")String descricao,
        @JsonAlias("imdbRating") Float avaliacao,
        @JsonAlias("totalSeasons") Integer temporadas) {
}
