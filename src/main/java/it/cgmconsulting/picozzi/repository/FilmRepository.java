package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Film;
import it.cgmconsulting.picozzi.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.picozzi.payload.response.FilmRentResponse;
import it.cgmconsulting.picozzi.payload.response.FilmRentableResponse;
import it.cgmconsulting.picozzi.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.languageId.languageId = :languageId")
    List<Film> findByLanguageId(@Param("languageId") long languageId);

    @Query(value = "SELECT new it.cgmconsulting.picozzi.payload.response.FilmRentResponse(" +
            "f.filmId, " +
            "f.title, " +
            "i.storeId.storeName) " +
            "FROM Rental r " +
            "JOIN r.id.inventoryId i " +
            "JOIN i.filmId f " +
            "WHERE r.id.customerId.customerId = :customerId " +
            "AND (r.rentalReturn IS NULL OR r.rentalReturn IS NOT NULL) " +
            "GROUP BY f.filmId, f.title, i.storeId.storeName"
    )
    List<FilmRentResponse> findAllFilmsRentByCustomer(@Param("customerId") long customerId);

    @Query(value = "SELECT new it.cgmconsulting.picozzi.payload.response.FilmMaxRentResponse(" +
            "f.filmId, " +
            "f.title, " +
            "COUNT(r)) " +
            "FROM Film f " +
            "JOIN Inventory i ON f = i.filmId " +
            "JOIN Rental r ON i = r.id.inventoryId " +
            "GROUP BY f.filmId, f.title " +
            "HAVING COUNT(r) = (" +
            "SELECT COUNT(r2) " +
            "FROM Film f2 " +
            "JOIN Inventory i2 ON f2 = i2.filmId " +
            "JOIN Rental r2 ON i2 = r2.id.inventoryId " +
            "GROUP BY f2.filmId " +
            "ORDER BY COUNT(r2) DESC " +
            "LIMIT 1)"
    )
    List<FilmMaxRentResponse> findFilmsWithMaxRentals();

    @Query(value = "SELECT DISTINCT new it.cgmconsulting.picozzi.payload.response.FilmResponse(" +
            "f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "f.languageId.languageName) " +
            "FROM Film f " +
            "JOIN f.filmStaffs fs " +
            "JOIN fs.staff s " +
            "JOIN fs.role r " +
            "WHERE r.roleName = 'ACTOR' " +
            "AND s.staffId IN :actorIds " +
            "GROUP BY f.filmId, f.title, f.description, f.releaseYear, f.languageId.languageName " +
            "HAVING COUNT(DISTINCT s.staffId) = :totalActors " +
            "ORDER BY f.title ASC"
    )
    List<FilmResponse> findFilmsByActorIds(
            @Param("actorIds") Collection<Long> actorIds,
            @Param("totalActors") long totalActors
    );

    @Query(nativeQuery = true, value = """
            SELECT 
                f.title as title,
                s.store_name as storeName,
                COUNT(DISTINCT i.inventory_id) as totalCopies,
                COUNT(DISTINCT i.inventory_id) - COALESCE(
                    (SELECT COUNT(DISTINCT r2.inventory_id)
                    FROM rental r2
                    WHERE r2.inventory_id = i.inventory_id
                    AND r2.rental_return IS NULL), 0
                ) as availableCopies
            FROM film f
            JOIN inventory i ON f.film_id = i.film_id
            JOIN store s ON s.store_id = i.store_id
            WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :title, '%'))
            GROUP BY f.title, s.store_name
            ORDER BY f.title ASC, s.store_name ASC
            """)
    List<FilmRentableResponse> findRentableFilms(@Param("title") String title);
}