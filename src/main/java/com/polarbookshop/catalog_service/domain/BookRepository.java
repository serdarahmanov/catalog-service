package com.polarbookshop.catalog_service.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Long> {

    Optional<Book> findByIsbn (String isbn);
    boolean existsByIsbn(String isbn);




    @Modifying //It tells Spring this query will not return anything.// Otherwise Spring expects something from db.

    @Transactional //The methods that update database state are
    // good candidates to make them transactional
    @Query("delete from Book where isbn = :isbn")
    void deleteByIsbn (String isbn);




}
