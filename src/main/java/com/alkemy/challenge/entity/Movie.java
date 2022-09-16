package com.alkemy.challenge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE movie_id=?")
@Where(clause = "deleted = false")
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Long id;

    @Column(name = "movie_name")
    private String name;

    @Column(name = "movie_image")
    private String image;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    @Column(name = "movie_rating")
    private Integer rating;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id", referencedColumnName = "character_id"))
    private Set<Character> characters = new HashSet<>();

}