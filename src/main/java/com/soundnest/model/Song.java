package com.soundnest.model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class Song {
    @Nullable
    private Long id;

    @NotBlank(message = "Naslov pesme ne sme biti prazan")
    private String title;

    private Integer duration;

    private Album album;
    private Artist artist;
    private Genre genre;

    @Nullable
    private String releaseDate;

    private boolean explicit;

    public Song() {
        this(null, "New Song", 0, null, null, null, null, false);
    }

    public Song(@Nullable Long id, String title, Integer duration, Album album,Artist artist, Genre genre, @Nullable String releaseDate, boolean isExplicit) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album =  album;
        this.artist = artist;
        this.genre =  genre;
        this.releaseDate = releaseDate;
        this.explicit = isExplicit;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Naslov pesme ne sme biti prazan") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Naslov pesme ne sme biti prazan") String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Nullable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@Nullable String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", album=" + album +
                ", artist=" + artist +
                ", genre=" + genre +
                ", releaseDate='" + releaseDate + '\'' +
                ", explicit=" + explicit +
                '}';
    }
}