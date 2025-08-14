package com.soundnest.service;


import com.soundnest.model.Artist;
import com.soundnest.repository.ArtistRepository;
import com.soundnest.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
    }

    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(Long artistId) {
        return artistRepository.findById(artistId);
    }

    public void deleteArtistById(Long artistId) {
        artistRepository.deleteById(artistId);
    }

    public List<Artist> search(
            String query,
            String present,
            String type,
            String sortBy,
            String direction
    ) {
        Comparator<Artist> comparator = Comparator.comparing(Artist::getId);

        if ("name".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Artist::getName);
        } else if ("country".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Artist::getCountry);
        } else if ("birthdate".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Artist::getBirthdate);
        }

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        return artistRepository.findAll().stream()
                .filter(artist -> query == null || artist.getName().toLowerCase().contains(query.toLowerCase()) || artist.getCountry().toLowerCase().contains(query.toLowerCase()))
                .filter(artist -> present == null || artist.getActiveYears().contains("present"))
                .filter(artist -> type == null || type.isEmpty() || artist.getType().equals(type))
                .sorted(comparator)
                .toList();
    }
}
