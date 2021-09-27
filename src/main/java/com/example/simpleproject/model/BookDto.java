package com.example.simpleproject.model;

import com.example.simpleproject.entity.Author;
import com.example.simpleproject.entity.BookEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BookDto {

    private Long id;
    private String name;
    private String date;

    @Pattern(regexp = "[0-9]{3}+-+[0-9]{1}+-+[0-9]{5}+-+[0-9]{3}+-+[0-9]{1}$")
    private String ISNBcode;
    private List<Author> authors;

    public BookDto(String name, String date, String ISNBcode, List<Author> authors) {
        this.name = name;
        this.date = date;
        this.ISNBcode = ISNBcode;
        this.authors = authors;
    }

    public static BookDto valueOf(BookEntity book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setDate(book.getDate());
        bookDto.setISNBcode(book.getISNBcode());
        bookDto.setAuthors(book.getAuthors());
        return bookDto;
    }

    public BookEntity mapToBookEntity(){
        BookEntity book = new BookEntity();
        book.setId(id);
        book.setName(name);
        book.setDate(date);
        book.setISNBcode(ISNBcode);
        book.setAuthors(authors);
        return book;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
