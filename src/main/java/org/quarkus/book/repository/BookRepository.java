package org.quarkus.book.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.quarkus.book.model.Book;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

}
