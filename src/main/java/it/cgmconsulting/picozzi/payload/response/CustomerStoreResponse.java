package it.cgmconsulting.picozzi.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerStoreResponse {
    private String storeName;
    private long totalCustomers;

    public CustomerStoreResponse(String storeName, long totalCustomers){
        this.storeName = storeName;
        this.totalCustomers = totalCustomers;
    }
}