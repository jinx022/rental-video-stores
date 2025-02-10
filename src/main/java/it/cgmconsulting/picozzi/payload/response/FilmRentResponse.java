package it.cgmconsulting.picozzi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class FilmRentResponse {
    private long filmId;
    private String title;
    private String storeName;
}