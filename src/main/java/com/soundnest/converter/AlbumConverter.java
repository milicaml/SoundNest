package com.soundnest.converter;


import com.soundnest.model.Album;
import com.soundnest.repository.AlbumRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AlbumConverter implements Converter<String, Album> {
    private final AlbumRepository albumRepository;

    public AlbumConverter(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album convert(String source) {
        try {
            Long albumId = Long.parseLong(source);
            return albumRepository.findById(albumId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}