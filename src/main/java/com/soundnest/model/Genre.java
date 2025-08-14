package com.soundnest.model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class Genre {
    @Nullable
    private Long id;

    @NotBlank(message = "Ime žanra ne sme biti prazno")
    private String name;

    @Nullable
    private String description;

    @Nullable
    private String originCountry;

    @Nullable
    private String originPeriod;

    public Genre() {
        this(null, "New Genre", null, null, null);
    }

    public Genre(@Nullable Long id, String name, @Nullable String description, @Nullable String originCountry, @Nullable String originPeriod) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.originCountry = originCountry;
        this.originPeriod = originPeriod;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Ime žanra ne sme biti prazno") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Ime žanra ne sme biti prazno") String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(@Nullable String originCountry) {
        this.originCountry = originCountry;
    }

    @Nullable
    public String getOriginPeriod() {
        return originPeriod;
    }

    public void setOriginPeriod(@Nullable String originPeriod) {
        this.originPeriod = originPeriod;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", originPeriod='" + originPeriod + '\'' +
                '}';
    }
}


