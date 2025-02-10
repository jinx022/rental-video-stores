package it.cgmconsulting.picozzi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FilmRequest {
    @NotBlank @Size(max = 100)
    private String title;

    @NotBlank @Size(max = 255)
    private String description;

    @NotBlank
    private int releaseYear;

    @NotBlank
    private long languageId;

    @NotBlank
    private long genreId;
}