package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest  //the slice test that covers only covers components that help to serialize domain objects
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> jsontester;   //It is a tool to test Json serialization and deserialization

    @Test
    void testSerialization() throws IOException {
        var book = new Book("1234567891", "Title", "Author", 9.90);
        var jsonContent = jsontester.write(book); // it can throw exception

        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialization() throws IOException {

        var content = """
                {
                "isbn" : "1234567891",
                "title" : "Title",
                "author" : "Author",
                "price" : 9.90
                }
                
                """;

        assertThat(jsontester.parse(content)) // parse method can throw IOException
                .usingRecursiveComparison() // Compares all the fields and embedded contents recursively
                .isEqualTo(new Book("1234567891", "Title", "Author", 9.90));
    }
}
