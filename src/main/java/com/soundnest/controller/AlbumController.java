package com.soundnest.controller;


import com.soundnest.model.Album;
import com.soundnest.service.AlbumService;
import com.soundnest.service.ArtistService;
import com.soundnest.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final GenreService genreService;

    public AlbumController(AlbumService albumService,ArtistService artistService, GenreService genreService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
    }
    //MVC - Model - View - Controller

    @GetMapping
    public String getAlbums(Model model) {
        model.addAttribute("albums", albumService.getAllAlbums());
        return "albums/list";
    }

    @GetMapping("/new")
    public String newAlbum(Model model) {
        model.addAttribute("album", new Album());
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("genres", genreService.getAllGenres());
        return "albums/form";
    }

    @PostMapping
    public String saveAlbum(@ModelAttribute @Valid Album album, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "albums/form"; // TODO : Maybe error page?
        }
        albumService.addAlbum(album);
        return "redirect:/albums";
    }

    @GetMapping("/edit/{id}")
    public String editAlbum(@PathVariable Long id, Model model) {
        Optional<Album> album = albumService.getAlbumById(id);
        if (album.isPresent()) {
            model.addAttribute("album", album.get());
            model.addAttribute("artists", artistService.getAllArtists());
            model.addAttribute("genres", genreService.getAllGenres());
            return "albums/form";
        }
        return "redirect:/albums";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
        return "redirect:/albums";
    }
}