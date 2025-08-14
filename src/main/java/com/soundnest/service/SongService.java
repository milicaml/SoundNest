package com.soundnest.service;


import com.soundnest.model.Song;
import com.soundnest.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
//    private final AlbumRepository albumRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
//        this.albumRepository = albumRepository;
    }

    public void addSong(Song song) {
        songRepository.save(song);
//        albumRepository.findById(song.getAlbumId()).get().setTotalTracks(+1);
    }

    public void editSong(Song song) {
        songRepository.save(song);
//        albumRepository.findById(song.getAlbumId()).get().setTotalTracks(-1);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> getAllSongsById(List<Long> ids) {
        return songRepository.findAll().stream().filter(song -> ids.contains(song.getId())).toList();
    }

    public Optional<Song> getSongById(Long songId) {
        return songRepository.findById(songId);
    }

    public List<Song> search(
            String query,
            Long albumId,
            Long artistId,
            Long genreId,
            Boolean explicit,
            String sortBy,
            String direction
    ) {
        Comparator<Song> comparator = Comparator.comparing(Song::getId);

        if ("title".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER);
        } else if ("duration".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Song::getDuration);
        } else if ("releaseDate".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Song::getReleaseDate);
        }

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        return songRepository.findAll().stream()
                .filter(song -> query == null || song.getTitle().toLowerCase().contains(query.toLowerCase()))
                .filter(song -> albumId == null || song.getAlbum().getId().equals(albumId))
                .filter(song -> artistId == null || song.getArtist().getId().equals(artistId))
                .filter(song -> genreId == null || song.getGenre().getId().equals(genreId))
                .filter(song -> explicit == null || song.isExplicit() == explicit)
                .sorted(comparator)
                .toList();
    }

    public void deleteSongById(Long songId) {
        songRepository.deleteById(songId);
//        albumRepository.findById(songRepository.findById(songId).get().getAlbumId()).get().setTotalTracks(-1);
    }
}