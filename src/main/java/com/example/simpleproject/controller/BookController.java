package com.example.simpleproject.controller;

import com.example.simpleproject.model.AuthorDto;
import com.example.simpleproject.model.BookDto;
import com.example.simpleproject.service.BookService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Api
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDto findBook(@PathVariable Long id){
        return bookService.findBook(id);
    }

    @GetMapping("/name/{name}")
    public BookDto findBookByName(@PathVariable String name){
        return bookService.findBookByName(name);
    }

    @GetMapping("/code/{code}")
    public BookDto findBookByCode(@PathVariable String code){
        return bookService.findBookByCode(code);
    }
    @GetMapping("/filter")
    public BookDto findBookByFilter(@PathVariable String code){
        return bookService.findBookByCode(code);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveBook(@RequestBody BookDto bookDto){
        return bookService.createBook(bookDto) ?
                new ResponseEntity<>(HttpStatus.CREATED):
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id)?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/edit")
    public ResponseEntity<HttpStatus> editBook(@RequestBody BookDto bookDto){
        return bookService.editBook(bookDto)?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
