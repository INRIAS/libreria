package com.books.libreria.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DataAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double descargas) {

}
