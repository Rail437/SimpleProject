package com.example.simpleproject.model;

import com.example.simpleproject.entity.Author;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthorDto {

    private Long id;
    private String name;
    private String surname;
    private String initials;

    public AuthorDto(String name, String surname, String initials) {
        this.name = name;
        this.surname = surname;
        this.initials = initials;
    }

    public static AuthorDto valueOf(Author author){
        return new AuthorDto(author.getName(),
                author.getSurname(),
                author.getInitials());
    }

    public Author mapToAuthor(){
        return new Author(name,surname,initials);
    }

}
