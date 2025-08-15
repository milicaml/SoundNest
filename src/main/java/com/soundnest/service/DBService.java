package com.soundnest.service;


import com.soundnest.model.*;
import com.soundnest.repository.*;
import com.soundnest.util.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DBService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    public DBService(UserRepository userRepository, ArtistRepository artistRepository, GenreRepository genreRepository,AlbumRepository albumRepository,SongRepository songRepository,PlaylistRepository playlistRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    @PostConstruct
    public void init() {
        try {
            CSVReader.CSVRecord userRecord = CSVReader.readCSV("data/users_data.csv");
            userRecord.forEachRow(
                    row -> new User(
                            row.parseNext(Long.class),         // id
                            row.next(),                        // username
                            row.next(),                        // password
                            row.next()                         // name
                    ),
                    userRepository::save
            );
            userRepository.setNextId(userRecord.getLastId()+1);

            CSVReader.CSVRecord artistRecord = CSVReader.readCSV("data/artist_data.csv");
            artistRecord.forEachRow(
                    row -> new Artist(
                            row.parseNext(Long.class),          // id
                            row.next(),                         // name
                            row.next(),                         // country
                            row.next(),                         // birthdate
                            row.next(),                         // type
                            row.next(),                         // activeYears
                            row.next(),                         // website
                            row.next()                          // imageUrl
                    ),
                    artistRepository::save
            );
            artistRepository.setNextId(artistRecord.getLastId()+1);

            CSVReader.CSVRecord genreRecord = CSVReader.readCSV("data/genre_data.csv");
            genreRecord.forEachRow(
                    row -> new Genre(
                            row.parseNext(Long.class),          // id
                            row.next(),                         // name
                            row.next(),                         // description
                            row.next(),                         // originCountry
                            row.next()                          // originPeriod
                    ),
                    genreRepository::save
            );
            genreRepository.setNextId(genreRecord.getLastId()+1);

            CSVReader.CSVRecord albumRecord = CSVReader.readCSV("data/album_data.csv");
            albumRecord.forEachRow(
                    row -> new Album(
                            row.parseNext(Long.class),          // id
                            row.next(),                         // title
                            row.next(),                         // releaseDate
                            row.parseNext(Long.class),          // artistId
                            row.parseNext(Long.class),          // genreId
                            row.next(),                         // type
                            row.next(),                         // label
                            row.next(),                         // coverUrl
                            row.parseNext(Integer.class)        // totalTracks
                    ),
                    albumRepository::save
            );
            albumRepository.setNextId(albumRecord.getLastId()+1);

            CSVReader.CSVRecord songRecord = CSVReader.readCSV("data/song_data.csv");
            songRecord.forEachRow(
                    row -> new Song(
                            row.parseNext(Long.class),                                          // id
                            row.next(),                                                         // title
                            row.parseNext(Integer.class),                                       // duration
                            albumRepository.findById(row.parseNext(Long.class)).get(),          // album
                            artistRepository.findById(row.parseNext(Long.class)).get(),         // artist
                            genreRepository.findById(row.parseNext(Long.class)).get(),          // genre
                            row.next(),                                                         // releaseDate
                            row.parseNext(Boolean.class)                                        // isExplicit
                    ),
                    songRepository::save
            );
            songRepository.setNextId(songRecord.getLastId()+1);

            CSVReader.CSVRecord playlistRecord = CSVReader.readCSV("data/playlist_data.csv");
            playlistRecord.forEachRow(
                    row->{
                        Playlist playlist = new Playlist(
                                row.parseNext(Long.class),      // id
                                row.next(),                     // name
                                row.next(),                     // description
                                row.next(),                     // creatorName
                                row.next(),                     // creationDate
                                row.parseNext(Boolean.class)    // publicPlaylist
                        );
                        String[] songIds = row.next().split(";");
                        playlist.setSongIds(Arrays.stream(songIds).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new)));
                        return playlist;
                    },
                    playlistRepository::save
            );
            playlistRepository.setNextId(playlistRecord.getLastId()+1);
        } catch (Exception e) {
            logger.info(e.toString());
        }
    }
}