package com.soundnest.service;



import com.soundnest.model.Album;
import com.soundnest.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void addAlbum(Album album) {
        albumRepository.save(album);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(Long albumId) {
        return albumRepository.findById(albumId);
    }

    public void deleteAlbumById(Long albumId) {
        albumRepository.deleteById(albumId);
    }
}