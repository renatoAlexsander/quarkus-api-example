package org.quarkus.book.service;

import org.quarkus.book.dto.BookDTO;
import org.quarkus.book.mapper.BookMapper;
import org.quarkus.book.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    @Transactional
    public Response save(BookDTO bookDTO) {
        if (Objects.nonNull(bookDTO.getId())) {
            var bookFounded = bookRepository.findById(bookDTO.getId());
            bookFounded.setName(bookDTO.getName());
            bookFounded.setPrice(bookDTO.getPrice());

            bookRepository.persistAndFlush(bookFounded);
            return Response.ok(bookFounded)
                    .build();
        }
        var book = BookMapper.INSTANCE.of(bookDTO);
        bookRepository.persist(book);
        return Response.created(URI.create("/api/books"))
                .entity(book)
                .build();
    }

    public Response findAll() {
        var books = bookRepository.findAll()
                .stream()
                .map(BookMapper.INSTANCE::of)
                .collect(Collectors.toSet());

        return Response.ok(books)
                .build();
    }

    public Response findById(Long id) {
        var book = bookRepository.findByIdOptional(id);

        if (book.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book not found.")
                    .build();
        }
        return Response.ok(BookMapper.INSTANCE.of(book.get()))
                .build();
    }

    @Transactional
    public Response deleteById(Long id) {
        bookRepository.deleteById(id);
        return Response.noContent()
                .build();
    }
}
