package it.cgmconsulting.picozzi.service;

import it.cgmconsulting.picozzi.entity.Customer;
import it.cgmconsulting.picozzi.entity.Inventory;
import it.cgmconsulting.picozzi.entity.Rental;
import it.cgmconsulting.picozzi.entity.RentalId;
import it.cgmconsulting.picozzi.exception.ResourceNotFoundException;
import it.cgmconsulting.picozzi.exception.BadRequestException;
import it.cgmconsulting.picozzi.repository.CustomerRepository;
import it.cgmconsulting.picozzi.repository.InventoryRepository;
import it.cgmconsulting.picozzi.repository.RentalRepository;
import it.cgmconsulting.picozzi.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Rental addUpdateRental(Long customerId, Long inventoryId, LocalDateTime rentalReturn) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        Optional<Rental> existingRental = rentalRepository.findActiveRentalByCustomerAndInventory(customerId, inventoryId);

        if (existingRental.isPresent()) {
            if (rentalReturn == null) {
                throw new BadRequestException("Return date is required for update");
            }
            Rental rental = existingRental.get();
            rental.setRentalReturn(rentalReturn);
            return rentalRepository.save(rental);
        } else {
            if (!isFilmAvailable(inventory.getInventoryId())) {
                throw new BadRequestException("Film not available for rental");
            }
            RentalId rentalId = new RentalId();
            rentalId.setCustomerId(customer);
            rentalId.setInventoryId(inventory);
            rentalId.setRentalDate(LocalDateTime.now());

            Rental newRental = new Rental();
            newRental.setId(rentalId);
            newRental.setRentalReturn(null);
            return rentalRepository.save(newRental);
        }
    }

    private boolean isFilmAvailable(Long inventoryId) {
        return rentalRepository.countActiveRentalsForInventory(inventoryId) == 0;
    }

    public long countRentalsInDateRangeByStore(Long storeId, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new BadRequestException("Start date must be before or equal to end date");
        }
        if (!storeRepository.existsById(storeId)) {
            throw new ResourceNotFoundException("Store not found");
        }
        return rentalRepository.countRentalsByStoreInDateRange(storeId, start, end);
    }
}