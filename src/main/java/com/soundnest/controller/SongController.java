package com.soundnest.controller;


import com.soundnest.model.Song;
import com.soundnest.service.AlbumService;
import com.soundnest.service.ArtistService;
import com.soundnest.service.GenreService;
import com.soundnest.service.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final GenreService genreService;

    public SongController(SongService songService, ArtistService artistService, AlbumService albumService, GenreService genreService) {
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getSongs(Model model) {
        model.addAttribute("songs", songService.getAllSongs());

        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("genres", genreService.getAllGenres());
        return "songs/list";
    }

    @GetMapping("/{id}")
    public String getSong(@PathVariable Long id, Model model) {
        Optional<Song> optionalSong = songService.getSongById(id);
        optionalSong.ifPresent(song -> {
            model.addAttribute("song", song);


            model.addAttribute("artist", song.getArtist());
            model.addAttribute("album", song.getAlbum());
            model.addAttribute("genre", song.getGenre());
        });
        if(!optionalSong.isPresent()) {
            return "redirect:/songs";
        }
        return "songs/detail";
    }

    @GetMapping("/new")
    public String newSong(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("genres", genreService.getAllGenres());
        return "songs/form";
    }

    @PostMapping
    public String saveSong(@ModelAttribute @Valid Song song, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "songs/form"; // TODO : Maybe error page
        }
        songService.addSong(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String editSong(@PathVariable Long id, Model model) {
        Optional<Song> song = songService.getSongById(id);
        if (song.isPresent()) {
            model.addAttribute("song", song.get());
            model.addAttribute("artists", artistService.getAllArtists());
            model.addAttribute("albums", albumService.getAllAlbums());
            model.addAttribute("genres", genreService.getAllGenres());
            return "songs/form";
        }
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return "redirect:/songs";
    }

    @GetMapping("/search")
    public String searchSongs(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Long albumId,
            @RequestParam(required = false) Long artistId,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Boolean explicit,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection,
            Model model) {
        List<Song> filteredSongs = songService.search(query, albumId, artistId, genreId, explicit,sortBy,sortDirection);
        model.addAttribute("songs", filteredSongs);

        model.addAttribute("songs", filteredSongs);
        model.addAttribute("query", query);
        model.addAttribute("albumId", albumId);
        model.addAttribute("artistId", artistId);
        model.addAttribute("genreId", genreId);
        model.addAttribute("explicit", explicit);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDirection", sortDirection);

        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("genres", genreService.getAllGenres());

        return "songs/list";
    }
}
