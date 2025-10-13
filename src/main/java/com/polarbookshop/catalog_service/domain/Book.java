package com.polarbookshop.catalog_service.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;
//Domain entity

public record Book(
        @Id
        Long id,

        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid."
        )
        String isbn,
        @NotBlank(message = "The book title must be defined.")
        String title,
        @NotBlank(message = "The book author must be defined.")
        String author,
        @NotNull(message = "The book price must be defined.")
        @Positive(message = "The book price must be greater then zero.")
        Double price,

        @CreatedDate
        Instant createdDate, //auditing fields .  Instant is UTC timestamp object.
        @LastModifiedDate
        Instant lastModifiedDate, //auditing fields

        @Version
        int version
) {

    public static Book of(String isbn, String title, String author, Double price) {
//null values are created by Spring Data under the hood.
//If id is null and version is 0 , Spring will create a record in db.

        //createdDate and LastModifiedDate is populated by Spring automatically.
        return new Book(null, isbn, title, author, price, null, null, 0);
    }
}
