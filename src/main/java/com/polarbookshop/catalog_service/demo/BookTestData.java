package com.polarbookshop.catalog_service.demo;

import com.polarbookshop.catalog_service.domain.Book;
import com.polarbookshop.catalog_service.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


// @Profile annotation marks beans to be registered to application context only
// when the specific profile is active .
// So, in this case , if we do spring.profiles.active=testData ,then this bean below will be registered

@Component
@Profile("testData")
public class BookTestData {

    private final BookRepository repository;

    public BookTestData(BookRepository repository) {
        this.repository = repository;
    }

//    This method will run after Flyway migrations has been called
//    So , the db is ready to save new records.
    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() {
        repository.deleteAll();
        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90);
        var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90);


        repository.saveAll(List.of(book1,book2));

    }
}
