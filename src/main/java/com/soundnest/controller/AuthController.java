package com.soundnest.controller;



import com.soundnest.model.SessionUser;
import com.soundnest.model.User;
import com.soundnest.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {
    private final SessionUser sessionUser;
    private final UserService userService;

    public AuthController(SessionUser sessionUser, UserService userService) {
        this.sessionUser = sessionUser;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        Optional<User> optionalUser = userService.login(username, password);
        if (optionalUser.isPresent()) {
            sessionUser.login(optionalUser.get());
            return "redirect:/";
        }
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name,
            Model model
    ) {
        Optional<User> optionalUser = userService.register(username, password, name);
        if (optionalUser.isPresent()) {
            // Registracija je uspela, šaljemo na login
            return "login";
        }
        // Registracija nije uspela – prikazujemo stare vrednosti
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("name", name);
        return "register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // briše celu sesiju
        return "redirect:/login";
    }
}