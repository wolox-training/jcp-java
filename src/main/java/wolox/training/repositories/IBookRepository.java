package wolox.training.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE book_author=:author", nativeQuery = true)
    Iterable<Book> findByBookAuthor(@Param("author") String author);

    @Query(value = "SELECT * FROM book b WHERE b.book_genre=:genre", nativeQuery = true)
    Iterable<Book> findByBookGenre(@Param("genre") String genre);
}
