package com.soundnest.converter;


import com.soundnest.model.Genre;
import com.soundnest.repository.GenreRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class GenreConverter implements Converter<String, Genre> {
    private final GenreRepository genreRepository;

    public GenreConverter(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre convert(String id) {
        try {
            Long genreId = Long.parseLong(id);
            return genreRepository.findById(genreId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}