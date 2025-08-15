package com.soundnest.converter;

import com.soundnest.model.Artist;
import com.soundnest.repository.ArtistRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArtistConverter implements Converter<String, Artist> {
    private final ArtistRepository artistRepository;

    public ArtistConverter(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist convert(String source) {
        try {
            Long artistId = Long.parseLong(source);
            return artistRepository.findById(artistId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}