package it.cgmconsulting.picozzi.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class FilmStaffId {
    private long filmId;
    private long staffId;
    private long roleId;
}