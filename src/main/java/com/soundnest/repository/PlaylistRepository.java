package com.soundnest.repository;



import com.soundnest.model.Playlist;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlaylistRepository {
    private final Map<Long, Playlist> store = new HashMap<>();
    private long nextId = 1;

    public void setNextId(long id) {
        this.nextId = id;
    }

    public void save(Playlist playlist) {
        if (playlist.getId() == null) {
            playlist.setId(nextId++);
        }
        store.put(playlist.getId(), playlist);
    }

    public Optional<Playlist> findById(Long playlistId) {
        return Optional.ofNullable(store.get(playlistId));
    }

    public List<Playlist> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsById(Long playlistId) {
        return store.containsKey(playlistId);
    }

    public boolean deleteById(Long playlistId) {
        return store.remove(playlistId) != null;
    }
}

