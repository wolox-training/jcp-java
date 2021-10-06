package wolox.training.repositories;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

/**
 * This class is responsible of handling the data according to the modification of the services and contains all the
 * logic of those mentioned above.
 */
@Repository
public class BookRepository implements IBookRepository {

    @Autowired
    IBookRepository bookRepository;

    /**
     * Constructor for books creation if necessary
     */
    public BookRepository() {

    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Iterable<Book> findByBookAuthor(String author) {
        return bookRepository.findByBookAuthor(author);
    }

    @Override
    public Iterable<Book> findByBookGenre(String genre) {
        return bookRepository.findByBookGenre(genre);
    }

    @Override
    public <S extends Book> S save(S entity) {
        return bookRepository.save(entity);
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return bookRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        bookRepository.deleteById(aLong);
    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
