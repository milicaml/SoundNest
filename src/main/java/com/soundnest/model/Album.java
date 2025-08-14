package com.soundnest.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class Album {
    @Nullable
    private Long id;

    @NotBlank(message = "Naziv albuma ne sme biti prazan")
    private String title;

    @Nullable
    private String releaseDate;

    private Long artistId;
    private Long genreId;

    private String type; // Studio, Visual Album, Compilation

    @Nullable
    private String label;

    @Nullable
    private String coverUrl;

    private int totalTracks;

    public Album() {
        this(null, "New Album", null, null, null, "Studio", null, null, 0);
    }

    public Album(@Nullable Long id, String title, @Nullable String releaseDate, Long artistId, Long genreId, String type, @Nullable String label, @Nullable String coverUrl, int totalTracks) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.artistId = artistId;
        this.genreId = genreId;
        this.type = type;
        this.label = label;
        this.coverUrl = coverUrl;
        this.totalTracks = totalTracks;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Naziv albuma ne sme biti prazan") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Naziv albuma ne sme biti prazan") String title) {
        this.title = title;
    }

    @Nullable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@Nullable String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public String getLabel() {
        return label;
    }

    public void setLabel(@Nullable String label) {
        this.label = label;
    }

    @Nullable
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(@Nullable String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", artistId=" + artistId +
                ", genreId=" + genreId +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", totalTracks=" + totalTracks +
                '}';
    }
}
