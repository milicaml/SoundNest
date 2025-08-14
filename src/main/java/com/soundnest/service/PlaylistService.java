package com.soundnest.service;


import com.soundnest.model.Playlist;
import com.soundnest.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void addPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> getPlaylistById(Long playlistId) {
        return playlistRepository.findById(playlistId);
    }

    public void deletePlaylistById(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        playlist.ifPresent(value -> value.addSongId(songId));
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        playlist.ifPresent(value -> value.removeSongId(songId));
    }
}
