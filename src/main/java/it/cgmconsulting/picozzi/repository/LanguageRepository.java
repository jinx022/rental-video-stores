package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}