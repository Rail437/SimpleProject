package com.example.simpleproject.service;

import com.example.simpleproject.entity.BookEntity;
import com.example.simpleproject.model.BookDto;
import com.example.simpleproject.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public boolean createBook(BookDto bookDto){
        if(bookRepo.findByISNBcode(bookDto.getISNBcode()) == null){
            BookEntity book = bookDto.mapToBookEntity();
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
}
