package wolox.training.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

    Optional<List<Book>> findByAuthor(String author);

    Optional<List<Book>> findByGenre(String genre);

    @Query(value = "select b from Book b", countQuery = "select count(b) from Book b")
    Page<Book> findAll(@Param(value = "page") Integer page, @Param("count") Integer countOfPages,
            @Param("sorting") String sorting, Pageable pageable);

    @Query(value = "SELECT b FROM Book b WHERE (:id is null OR b.id = :id) AND "
            + "(:genre is null OR b.genre = :genre) AND (:author is null OR b.author = :author) "
            + "AND (:image is null OR b.image = :image) AND (:title is null OR b.title = :title) "
            + "AND (:subtitle is null OR b.subtitle = :subtitle) AND (:publisher is null OR b.publisher = :publisher) "
            + "AND (:year is null OR b.year = :year) AND (:pages is null OR b.pages = :pages) "
            + "AND (:isbn is null OR b.isbn = :isbn)")
    Optional<List<Book>> getAll(@Param(value = "id") Long id, @Param(value = "genre") String genre,
            @Param(value = "author") String author, @Param(value = "image") String image,
            @Param(value = "title") String title, @Param(value = "subtitle") String subtitle,
            @Param(value = "publisher") String publisher, @Param(value = "year") String year,
            @Param(value = "pages") Integer pages, @Param(value = "isbn") String isbn);

    @Query(value = "SELECT b FROM Book b WHERE (:publisher is null or b.publisher = :publisher) AND "
            + "(:genre is null OR b.genre = :genre) AND (:year is null OR b.year = :year)")
    Optional<List<Book>> findByPublisherAndGenreAndYear(@Param(value = "publisher") String publisher,
            @Param(value = "genre") String genre, @Param(value = "year") String year);
}
