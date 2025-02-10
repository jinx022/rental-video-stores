package it.cgmconsulting.picozzi.controller;

import it.cgmconsulting.picozzi.payload.response.CustomerStoreResponse;
import it.cgmconsulting.picozzi.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    /**
     * #4 Controller
     * Get the number of customers who have rented a movie in a store
     *
     * @param storeName The name of the store to control
     * @return Call the service to return the CustomerStoreResponse
     */
    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity<CustomerStoreResponse> getCustomersCountByStore(@PathVariable String storeName) {
        return ResponseEntity.ok(storeService.getCustomersCountByStore(storeName));
    }
}