package com.soundnest.config;


import com.soundnest.converter.AlbumConverter;
import com.soundnest.converter.ArtistConverter;
import com.soundnest.converter.GenreConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    private final GenreConverter genreConverter;
    private final AlbumConverter albumConverter;
    private final ArtistConverter artistConverter;

    @Autowired
    public WebConfig(AuthInterceptor authInterceptor, GenreConverter genreConverter, AlbumConverter albumConverter, ArtistConverter artistConverter) {
        this.authInterceptor = authInterceptor;
        this.genreConverter = genreConverter;
        this.albumConverter = albumConverter;
        this.artistConverter = artistConverter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/css/**", "/js/**", "/images/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(genreConverter);
        registry.addConverter(albumConverter);
        registry.addConverter(artistConverter);
    }
}