package it.cgmconsulting.picozzi.service;

import it.cgmconsulting.picozzi.exception.ResourceNotFoundException;
import it.cgmconsulting.picozzi.payload.response.CustomerStoreResponse;
import it.cgmconsulting.picozzi.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public CustomerStoreResponse getCustomersCountByStore(String storeName){
        return storeRepository.countCustomersByStore(storeName)
                .orElseThrow(() -> new ResourceNotFoundException("Store with name '" + storeName + "' not found"));
    }
}