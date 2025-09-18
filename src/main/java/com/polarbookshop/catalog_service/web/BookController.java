package com.polarbookshop.catalog_service.web;

import com.polarbookshop.catalog_service.domain.Book;
import com.polarbookshop.catalog_service.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController //It is part of Spring MVC
@RequestMapping("books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Book> get(){

        return service.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn){
        //Exception added
        return service.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //returns 201 status if the book is created successfully
    public Book post(@Valid @RequestBody Book book ){
        //Exception added
        return service.addBookToCatalog(book);

    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Returns 204 status if the book is deleted successfully
    public void delete(@PathVariable String isbn){
        service.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book put (@PathVariable String isbn ,@Valid @RequestBody Book book){
        return service.editBookDetails(isbn, book);
    }
}
