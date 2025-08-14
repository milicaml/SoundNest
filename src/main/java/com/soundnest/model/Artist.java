package com.soundnest.model;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public class Artist {
    @Nullable
    private Long id;

    @NotBlank(message = "Ime izvodjaca ne sme biti prazno")
    private String name;

    @Nullable
    private String country;

    @NotBlank(message = "Datum rođenja izvođača ne može biti izostavljen")
    private String birthdate;

    @NotBlank(message = "Tip izvođača ne može biti izostavljen")
    private String type; // Solo, Band

    @Nullable
    private String activeYears;

    @Nullable
    private String website;

    @Nullable
    private String imageUrl;

    public Artist() {
        this(null, "New Artist", null, null, "Solo", null, null, null);
    }

    public Artist(@Nullable Long id, String name, @Nullable String country, String birthdate, String type, @Nullable String activeYears, @Nullable String website, @Nullable String imageUrl) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.birthdate = birthdate;
        this.type = type;
        this.activeYears = activeYears;
        this.website = website;
        this.imageUrl = imageUrl;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Ime izvodjaca ne sme biti prazno") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Ime izvodjaca ne sme biti prazno") String name) {
        this.name = name;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    public void setCountry(@Nullable String country) {
        this.country = country;
    }

    public @NotBlank(message = "Datum rođenja izvođača ne može biti izostavljen") String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotBlank(message = "Datum rođenja izvođača ne može biti izostavljen") String birthdate) {
        this.birthdate = birthdate;
    }

    public @NotBlank(message = "Tip izvođača ne može biti izostavljen") String getType() {
        return type;
    }

    public void setType(@NotBlank(message = "Tip izvođača ne može biti izostavljen") String type) {
        this.type = type;
    }

    @Nullable
    public String getActiveYears() {
        return activeYears;
    }

    public void setActiveYears(@Nullable String activeYears) {
        this.activeYears = activeYears;
    }

    @Nullable
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@Nullable String website) {
        this.website = website;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", type='" + type + '\'' +
                ", activeYears='" + activeYears + '\'' +
                ", website='" + website + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}