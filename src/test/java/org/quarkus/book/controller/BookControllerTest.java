package org.quarkus.book.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.quarkus.book.model.Book;
import org.quarkus.book.service.BookService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class BookControllerTest {

    private static final String BOOKS_ENDPOINT = "/api/books";

    @InjectMock
    BookService bookService;

    @Test
    public void shouldReturnAllBooks() {
        when(bookService.findAll())
            .thenReturn(
                Response.ok(getBooks())
                    .build());

        given()
            .when()
            .get(BOOKS_ENDPOINT)
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", is(2));

        verify(bookService).findAll();
    }

    private Set<Book> getBooks() {
        return Set.of(
            Book.builder()
                .id(1L)
                .name("JAVA 8")
                .price(BigDecimal.valueOf(100))
                .registerAt(LocalDateTime.now())
                .build(),
            Book.builder()
                .id(2L)
                .name("JAVA 11")
                .price(BigDecimal.valueOf(99))
                .registerAt(LocalDateTime.now())
                .build()
        );
    }

    @Test
    public void shouldGetBookById() {
        when(bookService.findById(1L))
            .thenReturn(Response.ok(getBook()).build());

        given()
            .when()
            .get(BOOKS_ENDPOINT + "/1")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", is(2))
            .body("name", equalTo("JAVA 11"))
            .body("price", is(99))
            .body("registerAt", notNullValue());

        verify(bookService).findById(1L);
    }

    private Book getBook() {
        return Book.builder()
            .id(2L)
            .name("JAVA 11")
            .price(BigDecimal.valueOf(99))
            .registerAt(LocalDateTime.now())
            .build();
    }

    @Test
    public void shouldDeleteBookById() {
        // todo delete book
    }

    @Test
    public void shouldSave() {
        // todo save a new book
    }
}