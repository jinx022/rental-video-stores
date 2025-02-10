package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Rental;
import it.cgmconsulting.picozzi.entity.RentalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {

    @Query(value = "SELECT COUNT(r) " +
            "FROM Rental r " +
            "WHERE r.id.inventoryId.inventoryId =:inventoryId " +
            "AND r.rentalReturn IS NULL")
    long countActiveRentalsForInventory(@Param("inventoryId") long inventoryId);

    @Query(value = "SELECT r " +
            "FROM Rental r " +
            "WHERE r.id.customerId.customerId = :customerId " +
            "AND r.id.inventoryId.inventoryId = :inventoryId " +
            "AND r.rentalReturn IS NULL " +
            "ORDER BY r.id.rentalDate DESC"
    )
    Optional<Rental> findActiveRentalByCustomerAndInventory(
            @Param("customerId") long customerId,
            @Param("inventoryId") long inventoryId
    );

    @Query(value = "SELECT COUNT(r) " +
            "FROM Rental r " +
            "WHERE r.id.inventoryId.storeId.storeId =:storeId " +
            "AND DATE(r.id.rentalDate) BETWEEN :startDate AND :endDate")
    long countRentalsByStoreInDateRange(
            @Param("storeId") Long storeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}