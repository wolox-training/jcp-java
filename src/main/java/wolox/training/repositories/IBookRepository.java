package wolox.training.repositories;

import java.util.List;
import java.util.Optional;
import wolox.training.models.Book;

public interface IBookRepository {

    Optional<List<Book>> findAll();

    Optional<List<Book>> findByAuthor(String author);

    Optional<Book> save(Book book);

    Optional<Book> update(Book book, Long id);

    void deleteById(Long id);

    Optional<Book> findById(Long id);
}
