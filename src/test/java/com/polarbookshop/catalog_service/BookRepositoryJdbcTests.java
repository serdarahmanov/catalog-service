package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.config.DataConfig;
import com.polarbookshop.catalog_service.domain.Book;
import com.polarbookshop.catalog_service.domain.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest //slice test for jdbc
@Import(DataConfig.class) //imports auditing class to application context
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//disables the embedded db
@ActiveProfiles("integration") //activates application-integration.yml config properties
public class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; //this a helper class that helps us
    // to persist data in db without repo so we can test our repos without depending on them.
    // Otherwise , we would need them to set up the db

    @Test
    void findBookByIsbnWhenExisting(){
        var bookIsbn = "1234567891";
        var book = Book.of("1234567891", "Title", "Author", 9.90);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}
