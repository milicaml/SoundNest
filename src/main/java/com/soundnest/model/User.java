package com.soundnest.model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class User {
    @Nullable
    private Long id;

    @NotBlank(message = "Korisničko ime mora biti definisano")
    private String username;

    @NotBlank(message = "Korisnička lozinka mora biti definisana")
    private String password;

    @NotBlank(message = "Ime korisnika mora biti definisano")
    private String name;

    public User() {
        this(null,null, null, null);
    }

    public User(@Nullable Long id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Korisničko ime mora biti definisano") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Korisničko ime mora biti definisano") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Korisnička lozinka mora biti definisana") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Korisnička lozinka mora biti definisana") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Ime korisnika mora biti definisano") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Ime korisnika mora biti definisano") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

