package com.example.simpleproject.service;

import com.example.simpleproject.entity.Author;
import com.example.simpleproject.entity.BookEntity;
import com.example.simpleproject.model.BookDto;
import com.example.simpleproject.repository.AuthorRepo;
import com.example.simpleproject.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public boolean createBook(BookDto bookDto){
        saveAuthor(bookDto.getAuthors());
        if(bookRepo.findByISNBcode(bookDto.getISNBcode()) == null){
            BookEntity book = bookDto.mapToBookEntity();
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < bookDto.getAuthors().size() ; i++) {
                authors.add(authorRepo.findByName(bookDto.getAuthors().get(i).getName()));
            }
            book.setAuthors(authors);
            bookRepo.save(book);
            return true;
        }
        return false;
    }

    public BookDto findBook(Long id) {
        BookDto bookDto;
        bookDto = bookRepo.findById(id).map(BookDto::valueOf).orElseThrow();
        return bookDto;
    }


    public boolean deleteBook(Long id) {
        if (bookRepo.findById(id).isPresent()){
            bookRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean editBook(BookDto bookDto) {
        Optional<BookEntity> book = bookRepo.findById(bookDto.getId());
        if(book.isPresent()){
            bookRepo.save(BookEntity.insertDtoData(bookDto));
            return true;
        }
        return false;
    }

    private void saveAuthor(List<Author> authors) {
        for (Author value : authors) {
            Author author = authorRepo.findByName(value.getName());
            if (author == null) {
                authorRepo.save(value);
            }
        }
    }

    public BookDto findBookByName(String name) {
        return BookDto.valueOf(bookRepo.findByName(name));
    }

    public BookDto findBookByCode(String code){
        return BookDto.valueOf(bookRepo.findByISNBcode(code));
    }
}
