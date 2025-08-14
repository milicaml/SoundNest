package com.soundnest.model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    @Nullable
    private Long id;

    @NotBlank(message = "Ime plejliste ne sme biti prazno")
    private String name;

    @NotBlank(message = "Opis plejliste ne sme biti prazan")
    private String description;

    @Nullable
    private String creatorName;

    @NotBlank(message = "Datum kreiranja mora biti definisan")
    private String creationDate;

    private boolean publicPlaylist;

    private List<Long> songIds;

    public Playlist() {
        this(null, "New Playlist", "Description", null, null, false);
    }

    public Playlist(@Nullable Long id, String name, String description, @Nullable String creatorName, String creationDate, boolean publicPlaylist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorName = creatorName;
        this.creationDate = creationDate;
        this.publicPlaylist = publicPlaylist;
        this.songIds = new ArrayList<>();
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Ime plejliste ne sme biti prazno") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Ime plejliste ne sme biti prazno") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Opis plejliste ne sme biti prazan") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Opis plejliste ne sme biti prazan") String description) {
        this.description = description;
    }

    @Nullable
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(@Nullable String creatorName) {
        this.creatorName = creatorName;
    }

    public @NotBlank(message = "Datum kreiranja mora biti definisan") String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NotBlank(message = "Datum kreiranja mora biti definisan") String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isPublicPlaylist() {
        return publicPlaylist;
    }

    public void setPublicPlaylist(boolean publicPlaylist) {
        this.publicPlaylist = publicPlaylist;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    public void addSongId(Long songId) {
        if (songIds.contains(songId)) return;
        songIds.add(songId);
    }

    public void removeSongId(Long songId) {
        if (!songIds.contains(songId)) return;
        songIds.remove(songId);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", publicPlaylist=" + publicPlaylist +
                ", songIds=" + songIds +
                '}';
    }
}
