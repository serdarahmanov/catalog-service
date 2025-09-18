package com.polarbookshop.catalog_service.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo){
        this.repo=repo;
    }
    public Iterable<Book> viewBookList(){
        return repo.findAll();
    }

    public Book viewBookDetails (String isbn){
        return repo.findByIsbn(isbn).orElseThrow(()-> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book){
        if(repo.existsByIsbn(book.isbn())){
            throw new BookAlreadyExistsException(book.isbn());
        }
        return repo.save(book);
    }



    public void removeBookFromCatalog(String isbn){
        repo.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book){
        return repo.findByIsbn(isbn).map(x-> {

            var bookToUpdate = new Book(
                    x.isbn(),
                    book.title(),
                    book.author(),
                    book.price()
            );
            return repo.save(bookToUpdate);
        }).orElseGet(()-> addBookToCatalog(book));
    }

}
