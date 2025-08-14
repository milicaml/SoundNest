package com.soundnest.controller;


import com.soundnest.model.Artist;
import com.soundnest.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public String getArtists(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        return "artists/list";
    }

    @GetMapping("/new")
    public String newArtist(Model model) {
        model.addAttribute(new Artist());
        return "artists/form";
    }

    @PostMapping
    public String saveArtist(@ModelAttribute @Valid Artist artist, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "artists/form"; // TODO : Maybe error page?
        }
        artistService.addArtist(artist);
        return "redirect:/artists";
    }

    @GetMapping("/edit/{id}")
    public String editArtist(@PathVariable Long id, Model model) {
        Optional<Artist> artist = artistService.getArtistById(id);
        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
            return "artists/form";
        }
        return "redirect:/artists";
    }

    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Long id) {
        artistService.deleteArtistById(id);
        return "redirect:/artists";
    }

    @GetMapping("/search")
    public String searchArtist(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String present,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Model model
    ) {
        List<Artist> filteredArtists = artistService.search(query, present, type, sortBy, direction);
        model.addAttribute("artists", filteredArtists);

        model.addAttribute("type", type);
        model.addAttribute("present", present);
        model.addAttribute("query", query);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "artists/list";
    }
}