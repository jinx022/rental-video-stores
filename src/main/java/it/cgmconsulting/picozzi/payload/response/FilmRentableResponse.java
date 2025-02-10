package it.cgmconsulting.picozzi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class FilmRentableResponse {
    private String title;
    private String storeName;
    private long totalCopies;
    private long availableCopies;
}