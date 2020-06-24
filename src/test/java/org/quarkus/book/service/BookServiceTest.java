package org.quarkus.book.service;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.quarkus.book.model.Book;
import org.quarkus.book.repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
class BookServiceTest {

    @Inject
    BookService bookService;

    @InjectMock
    BookRepository bookRepository;

    @Test
    public void shouldFindBookById() {
        when(bookRepository.findByIdOptional(1L))
            .thenReturn(Optional.of(getOneBook()));

        assertThat(bookService.findById(1L))
            .isInstanceOf(Response.class)
            .extracting("status")
            .isEqualTo(200);

        verify(bookRepository).findByIdOptional(1L);
    }

    @Test
    public void notShouldFindBookById() {
        when(bookRepository.findByIdOptional(anyLong())).thenReturn(Optional.empty());

        assertThat(bookService.findById(1L))
            .isInstanceOf(Response.class)
            .extracting("status", "entity")
            .containsExactly(404, "Book not found.");

        verify(bookRepository).findByIdOptional(anyLong());
    }

    @Test
    public void shouldDeleteBookById() {
        assertThat(bookService.deleteById(1L))
            .isInstanceOf(Response.class)
            .extracting("status", "entity")
            .containsExactly(204, null);

        verify(bookRepository).deleteById(anyLong());
    }

    @Test
    public void shouldFindAllBooks() {
        // todo find all books
    }

    @Test
    public void shouldSaveBook() {
        // todo save a new book
    }

    @Test
    public void shouldSaveExistentBook() {
        // todo save an existent book
    }

    private Book getOneBook() {
        return Book.builder()
            .id(1L)
            .name("JAVA 8")
            .registerAt(LocalDateTime.now())
            .price(BigDecimal.valueOf(50))
            .build();
    }
}