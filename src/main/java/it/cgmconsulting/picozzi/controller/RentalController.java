package it.cgmconsulting.picozzi.controller;

import it.cgmconsulting.picozzi.entity.Rental;
import it.cgmconsulting.picozzi.payload.response.RentalResponse;
import it.cgmconsulting.picozzi.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    /**
     * #5 Controller
     * Add a new rental or Update one
     *
     * @param customerId Customer id of the renter
     * @param inventoryId Inventory id where to check
     * @param rentalReturn Return date of the rental
     * @return Return a ResponseEntity with HTTP status ok and a RentalResponse
     */
    @PutMapping("/add-update-rental/{customerId}/{inventoryId}")
    public ResponseEntity<RentalResponse> addUpdateRental(
            @PathVariable long customerId,
            @PathVariable long inventoryId,
            @RequestParam(required = false) LocalDateTime rentalReturn) {
        Rental rental = rentalService.addUpdateRental(customerId, inventoryId, rentalReturn);
        RentalResponse response = new RentalResponse(
                rental.getId().getCustomerId().getCustomerId(),
                rental.getId().getInventoryId().getInventoryId(),
                rental.getId().getRentalDate(),
                rental.getRentalReturn(),
                rental.getRentalReturn() == null ? "ACTIVE" : "RETURNED"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * #6 Controller
     * Get the number of rentals of a store in a given time period
     *
     * @param storeId Store id to control
     * @param start Start day of control
     * @param end End day of control
     * @return Return a ResponseEntity with HTTP status ok and the number of rentals
     */
    @GetMapping("/count-rentals-in-date-range-by-store/{storeId}")
    public ResponseEntity<Long> countRentalsInDateRangeByStore(
            @PathVariable Long storeId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        long count = rentalService.countRentalsInDateRangeByStore(storeId, start, end);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}