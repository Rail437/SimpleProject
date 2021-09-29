package com.example.simpleproject.entity;

import com.example.simpleproject.model.BookDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "books")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String date;
    private String ISNBcode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    public static BookEntity insertDtoData(BookDto bookDto){
        return bookDto.mapToBookEntity();
    }
}
