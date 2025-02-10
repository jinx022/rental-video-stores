package it.cgmconsulting.picozzi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class FilmMaxRentResponse {
    private long filmId;
    private String title;
    private long totalRentals;
}