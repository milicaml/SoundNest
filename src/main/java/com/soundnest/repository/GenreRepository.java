package com.soundnest.repository;

import com.soundnest.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GenreRepository {
    private final Map<Long, Genre> store = new HashMap<>();
    private long nextId = 1;

    public void setNextId(long id) {
        this.nextId = id;
    }

    public void save(Genre genre) {
        if (genre.getId() == null) {
            genre.setId(nextId++);
        }
        store.put(genre.getId(), genre);
    }

    public Optional<Genre> findById(Long genreId) {
        return Optional.ofNullable(store.get(genreId));
    }

    public List<Genre> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsById(Long genreId) {
        return store.containsKey(genreId);
    }

    public boolean deleteById(Long genreId) {
        return store.remove(genreId) != null;
    }
}