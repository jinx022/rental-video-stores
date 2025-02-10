package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}