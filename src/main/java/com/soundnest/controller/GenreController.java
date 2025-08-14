package com.soundnest.controller;


import com.soundnest.model.Genre;
import com.soundnest.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String genres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "genres/list";
    }

    @GetMapping("/new")
    public String newGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/form";
    }

    @PostMapping
    public String newGenre(@ModelAttribute @Valid Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genres/form"; // TODO : Maybe error page?
        }
        genreService.addGenre(genre);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String editGenre(@PathVariable Long id, Model model) {
        Optional<Genre> genre = genreService.getGenreById(id);
        if (genre.isPresent()) {
            model.addAttribute("genre", genre.get());
            return "genres/form";
        }
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.deleteGenreById(id);
        return "redirect:/genres";
    }
}
