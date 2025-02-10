package it.cgmconsulting.picozzi.service;

import it.cgmconsulting.picozzi.entity.Film;
import it.cgmconsulting.picozzi.entity.Genre;
import it.cgmconsulting.picozzi.entity.Language;
import it.cgmconsulting.picozzi.exception.ResourceNotFoundException;
import it.cgmconsulting.picozzi.payload.request.FilmRequest;
import it.cgmconsulting.picozzi.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.picozzi.payload.response.FilmRentResponse;
import it.cgmconsulting.picozzi.payload.response.FilmRentableResponse;
import it.cgmconsulting.picozzi.payload.response.FilmResponse;
import it.cgmconsulting.picozzi.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public FilmResponse updateFilm(FilmRequest request, long filmId){
        Film film = filmRepository.findById(filmId)
                .orElseThrow(()-> new ResourceNotFoundException("Film with id: " + filmId + " not found"));
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(()-> new ResourceNotFoundException("Language with id: " + request.getLanguageId() + " not found"));
        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(()-> new ResourceNotFoundException("Genre with id: " + request.getGenreId() + " not found"));
        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setLanguageId(language);
        film.setGenreId(genre);
        FilmResponse filmResponse = FilmResponse.fromFilm(film);
        return filmResponse;
    }

    public List<FilmResponse> findFilmsByLanguage(long languageId){
        if (!languageRepository.existsById(languageId)) {
            throw new ResourceNotFoundException("Language with id: " + languageId + " not found");
        }
        List<Film> films = filmRepository.findByLanguageId(languageId);
        List<FilmResponse> responses = new ArrayList<>();
        for (Film film : films) {
            responses.add(FilmResponse.fromFilmWithLanguageName(film));
        }
        return responses;
    }

    public List<FilmRentResponse> findAllFilmsRentByCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer with id: " + customerId + " not found");
        }
        return filmRepository.findAllFilmsRentByCustomer(customerId);
    }

    public List<FilmMaxRentResponse> findFilmsWithMaxRentals() {
        return filmRepository.findFilmsWithMaxRentals();
    }

    public List<FilmResponse> findFilmsByActors(Collection<Long> actorIds) {
        if (actorIds == null || actorIds.isEmpty()) {
            throw new IllegalArgumentException("Actor IDs list cannot be empty");
        }
        for (Long actorId : actorIds) {
            if (!staffRepository.existsById(actorId)) {
                throw new ResourceNotFoundException("Actor with id: " + actorId + " not found");
            }
        }
        return filmRepository.findFilmsByActorIds(actorIds, actorIds.size());
    }

    public List<FilmRentableResponse> findRentableFilms(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title parameter cannot be null or empty");
        }
        return filmRepository.findRentableFilms(title);
    }
}