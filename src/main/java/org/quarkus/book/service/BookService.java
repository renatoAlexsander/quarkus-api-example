package org.quarkus.book.service;

import org.quarkus.book.dto.BookDTO;
import org.quarkus.book.mapper.BookMapper;
import org.quarkus.book.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    @Transactional
    public void save(BookDTO bookDTO) {
        bookRepository.persist(BookMapper.INSTANCE.of(bookDTO));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll()
            .stream()
            .map(BookMapper.INSTANCE::of)
            .collect(Collectors.toList());
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
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
