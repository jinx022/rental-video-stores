package it.cgmconsulting.picozzi.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RentalResponse {
    private long customerId;
    private long inventoryId;
    private LocalDateTime rentalDate;
    private LocalDateTime rentalReturn;
    private String status;

    public RentalResponse(long customerId, long inventoryId, LocalDateTime rentalDate,
                          LocalDateTime rentalReturn, String status){
        this.customerId = customerId;
        this.inventoryId = inventoryId;
        this.rentalDate = rentalDate;
        this.rentalReturn = rentalReturn;
        this.status = status;
    }
}