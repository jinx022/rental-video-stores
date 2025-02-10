package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Film;
import it.cgmconsulting.picozzi.entity.Inventory;
import it.cgmconsulting.picozzi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByStoreIdAndFilmId(Store store, Film film);
}