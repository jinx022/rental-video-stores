package it.cgmconsulting.picozzi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", updatable = false)
    private long filmId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language languageId;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genreId;

    @OneToMany(mappedBy = "film")
    private Set<FilmStaff> filmStaffs;
}