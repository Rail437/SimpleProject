package com.example.simpleproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;
    private String surname;
    private String initials;

    public Author() {
    }

    public Author(String name, String surname, String initials) {
        this.name = name;
        this.surname = surname;
        this.initials = initials;
    }
}
