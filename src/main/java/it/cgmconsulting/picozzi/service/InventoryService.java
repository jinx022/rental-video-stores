package it.cgmconsulting.picozzi.service;

import it.cgmconsulting.picozzi.entity.Film;
import it.cgmconsulting.picozzi.entity.Inventory;
import it.cgmconsulting.picozzi.entity.Store;
import it.cgmconsulting.picozzi.exception.ResourceNotFoundException;
import it.cgmconsulting.picozzi.repository.FilmRepository;
import it.cgmconsulting.picozzi.repository.InventoryRepository;
import it.cgmconsulting.picozzi.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;
    private final FilmRepository filmRepository;

    @Transactional
    public void addFilmToStore(long storeId, long filmId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + storeId));

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found with id: " + filmId));
        if (inventoryRepository.existsByStoreIdAndFilmId(store, film)) {
            throw new IllegalStateException("Film already exists in this store");
        }
        Inventory inventory = new Inventory();
        inventory.setStoreId(store);
        inventory.setFilmId(film);
        inventoryRepository.save(inventory);
    }
}