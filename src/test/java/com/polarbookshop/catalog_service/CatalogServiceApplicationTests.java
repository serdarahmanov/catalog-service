package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest gives full Spring context so that we can run integration tests

// webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT -- this setting gives
// as running server on a random port

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired // Utility to perform Rest call for testing
    private WebTestClient testClient;


    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = new Book("1234567891", "Title", "Author", 9.90);
        testClient
                .post()

                .uri("/books")

                .bodyValue(expectedBook) // send the Book as JSON

                .exchange()

                .expectStatus().isCreated()//expects 201 status code - created

                .expectBody(Book.class) // deserialize response as Book

                .value(actualBook ->
                {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
                });
    }

}
