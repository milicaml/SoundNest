package com.soundnest.repository;


import com.soundnest.model.Album;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AlbumRepository {
    private final Map<Long, Album> store = new HashMap<>();
    private long nextId = 1;

    public void setNextId(long id) {
        this.nextId = id;
    }

    public void save(Album album) {
        if (album.getId() == null) {
            album.setId(nextId++);
        }
        store.put(album.getId(), album);
    }

    public Optional<Album> findById(Long albumId) {
        return Optional.ofNullable(store.get(albumId));
    }

    public List<Album> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsById(Long albumId) {
        return store.containsKey(albumId);
    }

    public boolean deleteById(Long albumId) {
        return store.remove(albumId) != null;
    }
}