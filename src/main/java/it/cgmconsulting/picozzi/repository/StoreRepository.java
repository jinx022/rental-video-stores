package it.cgmconsulting.picozzi.repository;

import it.cgmconsulting.picozzi.entity.Store;
import it.cgmconsulting.picozzi.payload.response.CustomerStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT new it.cgmconsulting.picozzi.payload.response.CustomerStoreResponse(" +
                    "s.storeName, " +
                    "COUNT(DISTINCT r.id.customerId)" +
                    ") FROM Store s " +
                    "JOIN Inventory i ON i.storeId = s " +
                    "JOIN Rental r ON r.id.inventoryId = i " +
                    "WHERE s.storeName = :storeName " +
                    "GROUP BY s.storeName"
    )
    Optional<CustomerStoreResponse> countCustomersByStore(@Param("storeName") String storeName);
}