package com.alkemy.challenge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id", nullable = false)
    private Long id;

    @Column(name = "character_image")
    private String image;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_age")
    private Integer age;

    @Column(name = "character_weight")
    private Integer weight;

    @Lob
    @Column(name = "character_story")
    private String story;


    @ManyToMany(mappedBy = "characters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Movie> movies = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Character character = (Character) o;
        return id != null && Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}