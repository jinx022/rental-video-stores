package it.cgmconsulting.picozzi.payload.response;

import it.cgmconsulting.picozzi.entity.Film;
import it.cgmconsulting.picozzi.entity.Genre;
import it.cgmconsulting.picozzi.entity.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmResponse {
    private long filmId;
    private String title;
    private String description;
    private int releaseYear;
    private Language languageId;
    private String languageName;
    private Genre genreId;
    
    public FilmResponse(long filmId, String title, String description, int releaseYear, Language languageId, Genre genreId) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.genreId = genreId;
    }

    public FilmResponse(long filmId, String title, String description, int releaseYear, String languageName) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageName = languageName;
    }

    public static FilmResponse fromFilm(Film film) {
        return new FilmResponse(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguageId(),
                film.getGenreId()
        );
    }

    public static FilmResponse fromFilmWithLanguageName(Film film){
        return new FilmResponse(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguageId().getLanguageName()
        );
    }
}