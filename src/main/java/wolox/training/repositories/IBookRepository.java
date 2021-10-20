package wolox.training.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

    Optional<List<Book>> findByAuthor(String author);

    Optional<List<Book>> findByGenre(String genre);
}
