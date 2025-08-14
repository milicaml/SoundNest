package com.soundnest.repository;


import com.soundnest.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArtistRepository {
    private final Map<Long, Artist> store = new HashMap<>();
    private long nextId = 1;

    public void setNextId(long id) {
        this.nextId = id;
    }

    public void save(Artist artist) {
        if (artist.getId() == null) {
            artist.setId(nextId++);
        }
        store.put(artist.getId(), artist);
    }

    public Optional<Artist> findById(Long artistId) {
        return Optional.ofNullable(store.get(artistId));
    }

    public List<Artist> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsById(Long artistId) {
        return store.containsKey(artistId);
    }

    public boolean deleteById(Long artistId) {
        return store.remove(artistId) != null;
    }
}
