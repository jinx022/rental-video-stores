package it.cgmconsulting.picozzi.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class RentalRequest {
    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Inventory ID cannot be null")
    private Long inventoryId;

    private LocalDateTime rentalReturn;
}