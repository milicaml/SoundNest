package com.soundnest.controller;


import com.soundnest.model.Playlist;
import com.soundnest.service.PlaylistService;
import com.soundnest.service.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final SongService songService;

    public PlaylistController(PlaylistService playlistService, SongService songService) {
        this.playlistService = playlistService;
        this.songService = songService;
    }

    @GetMapping
    public String getPlaylists(Model model) {
        model.addAttribute("playlists", playlistService.getAllPlaylists());
        return "playlists/list";
    }

    @GetMapping("/new")
    public String newPlaylist(Model model) {
        model.addAttribute("playlist", new Playlist());
        model.addAttribute("songs", new ArrayList<>());
        return "playlists/form";
    }

    @PostMapping
    public String savePlaylist(@ModelAttribute @Valid Playlist playlist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "playlists/form"; // TODO : Maybe error page?
        }
        playlistService.addPlaylist(playlist);
        return "redirect:/playlists";
    }

    @GetMapping("/edit/{id}")
    public String editPlaylist(@PathVariable Long id, Model model) {
        Optional<Playlist> playlist = playlistService.getPlaylistById(id);
        if (playlist.isPresent()) {
            model.addAttribute("playlist", playlist.get());
            model.addAttribute("songs", songService.getAllSongsById(playlist.get().getSongIds()));
            return "playlists/form";
        }
        return "redirect:/playlists";
    }

    @GetMapping("/delete/{id}")
    public String deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylistById(id);
        return "redirect:/playlists";
    }

    @PostMapping("/{playlistId}/add-song/{songId}")
    public String addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return "redirect:/songs/edit/" + songId;
    }

    @PostMapping("/{playlistId}/remove-song/{songId}")
    public String removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
        return "redirect:/playlists/edit/" + playlistId;
    }
}