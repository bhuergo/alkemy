package com.alkemyjava.Huergo.mapper;

import com.alkemyjava.Huergo.entities.Movie;
import com.alkemyjava.Huergo.entities.MovieDTO;

public class movieMapper {

    public MovieDTO toDTO(Movie movie) {
        MovieDTO mDto = new MovieDTO();
        mDto.setImage(movie.getImage());
        mDto.setTitle(movie.getTitle());
        mDto.setCreationDate(movie.getCreationDate());
        return mDto;
    }
}
