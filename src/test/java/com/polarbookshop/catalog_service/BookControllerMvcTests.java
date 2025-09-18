package com.polarbookshop.catalog_service;


import com.polarbookshop.catalog_service.domain.BookNotFoundException;
import com.polarbookshop.catalog_service.domain.BookService;
import com.polarbookshop.catalog_service.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//No embedded test runs, only mock environment
@WebMvcTest (BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc; //it is a tool to simulate http requests in mock environment

    @MockitoBean
    private BookService bookService; // it is mocked bean loaded to slice test spring context

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "73737313940";

//        Behavior-Driven-Development syntax used in test : Given -When -Then
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);

//        the http mocking tool is being used
        mockMvc.perform(get("/books/"+isbn)).andExpect(status().isNotFound());
    }

}
