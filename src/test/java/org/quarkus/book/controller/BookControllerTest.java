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
                .get("/api/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", is(2))
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("JAVA 8"))
                .body("[0].price", equalTo(100))
                .body("[0].registerAt", notNullValue())
                .body("[0].editedAt", nullValue());

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
}