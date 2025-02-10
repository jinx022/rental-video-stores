package it.cgmconsulting.picozzi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@Table(name = "rental")
public class Rental {
    @EmbeddedId
    private RentalId id;

    @Column(name = "rental_return")
    private LocalDateTime rentalReturn;
}