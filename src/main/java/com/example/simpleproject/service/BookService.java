package com.example.simpleproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.simpleproject.entity.Author;
import com.example.simpleproject.model.BookDto;
import com.example.simpleproject.entity.BookEntity;
import com.example.simpleproject.repository.AuthorRepo;
import com.example.simpleproject.repository.BookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
        BookDto bookDto = null;
        try {
            bookDto = bookRepo.findById(id).map(BookDto::valueOf).orElseThrow();
        }catch (NoSuchElementException exception){
            exception.printStackTrace();
        }
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
        BookDto bookDto = null;
        try {
            bookDto = BookDto.valueOf(bookRepo.findByISNBcode(code));
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        return bookDto;
    }
    public List<BookDto> findBookByAuthor(String author){
        List<BookDto> bookDtos = new ArrayList<>();
        List<BookEntity> bookEntities = bookRepo.findAllByAuthors(author).stream().toList();
        for (BookEntity bookEntity : bookEntities) {
            bookDtos.add(BookDto.valueOf(bookEntity));
        }
        return bookDtos;
    }

    public List<BookDto> findMatchingBooks(BookEntity bookEntityFilter) {
        return bookRepo.findAll(Example.of(bookEntityFilter)).stream().map(BookDto::valueOf).collect(Collectors.toList());
    }

    public List<BookDto> findMatchingBooksAndSort(BookEntity bookEntityFilter, String sort) {
        List<BookDto> bookDtoList;
        if(!sort.isEmpty()){
            bookDtoList = bookRepo.findAll(Example.of(bookEntityFilter), Sort.by(sort)).stream().map(BookDto::valueOf).collect(Collectors.toList());
        }else {
            bookDtoList = bookRepo.findAll(Example.of(bookEntityFilter)).stream().map(BookDto::valueOf).collect(Collectors.toList());
        }
        return bookDtoList;
    }
}
