package it.cgmconsulting.picozzi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private long inventoryId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store storeId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film filmId;
}