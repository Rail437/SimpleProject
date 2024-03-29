package com.example.simpleproject.controller;

import com.example.simpleproject.entity.BookEntity;
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

    /**
     * FindByFilter
     * Поиск книг по фильтру.
     */
    @PostMapping("/match")
    public List<BookDto> getMatchingBook(@RequestBody BookEntity bookEntity) {
        return bookService.findMatchingBooks(bookEntity);
    }

    @RequestMapping(value = "/param", method = RequestMethod.POST, params = "sort")
    public List<BookDto> getByParamBook(@RequestBody BookEntity bookEntity, @RequestParam("sort") String sort) {
        return bookService.findMatchingBooksAndSort(bookEntity,sort);
    }

    @GetMapping("/name/{name}")
    public BookDto findBookByName(@PathVariable String name){
        return bookService.findBookByName(name);
    }

    @GetMapping("/code/{code}")
    public BookDto findBookByCode(@PathVariable String code){
        return bookService.findBookByCode(code);
    }
    @GetMapping("/author/{author}")
    public List<BookDto> findBookByAuthor(@PathVariable String author){
        return bookService.findBookByAuthor(author);
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
