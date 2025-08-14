package com.soundnest.service;


import com.soundnest.model.Genre;
import com.soundnest.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    public void deleteGenreById(Long genreId) {
        genreRepository.deleteById(genreId);
    }
}