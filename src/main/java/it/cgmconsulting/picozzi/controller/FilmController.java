package it.cgmconsulting.picozzi.controller;

import it.cgmconsulting.picozzi.payload.request.FilmRequest;
import it.cgmconsulting.picozzi.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.picozzi.payload.response.FilmRentResponse;
import it.cgmconsulting.picozzi.payload.response.FilmRentableResponse;
import it.cgmconsulting.picozzi.payload.response.FilmResponse;
import it.cgmconsulting.picozzi.service.FilmService;
import it.cgmconsulting.picozzi.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final InventoryService inventoryService;

    /**
     * #1 Controller
     * To update a movie infos except the id
     *
     * @param request Movie fields that can be updated
     * @param filmId Movie id to update
     * @return Call the service to update and return the FilmResponse
     */
    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<FilmResponse> update(@RequestBody FilmRequest request, @PathVariable long filmId){
        return ResponseEntity.ok(filmService.updateFilm(request, filmId));
    }

    /**
     * #2 Controller
     * Get a movies list by language
     *
     * @param languageId Language id of the movies
     * @return Call the service to return a List of FilmResponse
     */
    @GetMapping("/find-films-by-language/{languageId}")
    public ResponseEntity<List<FilmResponse>> findFilmsByLanguage(@PathVariable long languageId) {
        return ResponseEntity.ok(filmService.findFilmsByLanguage(languageId));
    }

    /**
     * #3 Controller
     * Add a movie to an inventory of a store
     *
     * @param storeId Store id where to save the movie
     * @param filmId Film id to save
     * @return Return a ResponseEntity with HTTP status created if the movie is added to the inventory store
     */
    @PostMapping("/add-film-to-store/{storeId}/{filmId}")
    public ResponseEntity<Void> addFilmToStore(@PathVariable long storeId, @PathVariable long filmId){
        inventoryService.addFilmToStore(storeId, filmId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * #7 Controller
     * Get all the movies rented by a customer
     *
     * @param customerId Customer id to check
     * @return Call the service to get a list of movies with HTTP status ok
     */
    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<List<FilmRentResponse>> findAllFilmsRentByCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(filmService.findAllFilmsRentByCustomer(customerId), HttpStatus.OK);
    }

    /**
     * #8 Controller
     * Get the most rented movies
     *
     * @return Call the service with HTTP status ok
     */
    @GetMapping("/find-film-with-max-number-of-rent")
    public ResponseEntity<List<FilmMaxRentResponse>> findFilmsWithMaxRentals() {
        return new ResponseEntity<>(filmService.findFilmsWithMaxRentals(), HttpStatus.OK);
    }

    /**
     * #9 Controller
     * Get the movies in which all the selected actors participated
     *
     * @param actorIds Actor id to check
     * @return Call the service with HTTP status ok
     */
    @GetMapping("/find-films-by-actors")
    public ResponseEntity<List<FilmResponse>> findFilmsByActors(
            @RequestParam Collection<Long> actorIds) {
        return new ResponseEntity<>(filmService.findFilmsByActors(actorIds), HttpStatus.OK);
    }

    /**
     * #10 Controller
     * Get movies held and available for rent by title
     *
     * @param title Movie title to check
     * @return Call the service with HTTP status ok
     */
    @GetMapping("/find-rentable-films")
    public ResponseEntity<List<FilmRentableResponse>> findRentableFilms(
            @RequestParam String title) {
        return new ResponseEntity<>(filmService.findRentableFilms(title), HttpStatus.OK);
    }
}