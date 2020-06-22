package org.quarkus.book.service;

import org.quarkus.book.dto.BookDTO;
import org.quarkus.book.mapper.BookMapper;
import org.quarkus.book.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookService {

    @Inject
    private BookRepository bookRepository;

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

    public BookDTO findById(Long id) {
        return BookMapper.INSTANCE.of(
            bookRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Book not found."))
        );
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
