package com.soundnest.repository;


import com.soundnest.model.Song;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SongRepository {
    private final Map<Long, Song> store = new HashMap<>();
    private long nextId = 1;

    public void setNextId(long id) {
        this.nextId = id;
    }

    public void save(Song song) {
        if (song.getId() == null) {
            song.setId(nextId++);
        }
        store.put(song.getId(), song);
    }

    public Optional<Song> findById(Long songId) {
        return Optional.ofNullable(store.get(songId));
    }

    public List<Song> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsById(Long songId) {
        return store.containsKey(songId);
    }

    public boolean deleteById(Long songId) {
        return store.remove(songId) != null;
    }
}