package wolox.training.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

/**
 * This class is responsible of handling the data according to the modification of the services and contains all the
 * logic of those mentioned above.
 * */
@Repository
public class BookRepository implements IBookRepository {

    private List<Book> books;
    private Book book;

    /**
     * Constructor for books creation if necessary
     * */
    public BookRepository() {
        books = new ArrayList<>();
        book = new Book(1L, "Action", "Juan Cruz Poli", "http://example", "Wolox Training", "Wolox", "2021", 155,
                "abc123");
        books.add(book);
        book = new Book(2L, "Terror", "Juan Perez", "http://example2", "Wolox Training2", "Wolox2", "2021", 188,
                "abc1234");
        books.add(book);
        book = new Book(3L, "Fantasy", "Juan Cruz Poli", "http://example3", "Wolox Training3", "Wolox3", "2021", 237,
                "abc12345");
        books.add(book);
    }

    @Override
    public Optional<List<Book>> findAll() {
        return Optional.of(books);
    }

    /**
     * @return the entire book of the selected author
     * @param author indicates the author to be searched
     * */
    @Override
    public Optional<List<Book>> findByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        try {
            books.stream().forEach(b -> {
                if (b.getAuthor().replace(" ", "").equals(author)) {
                    authorBooks.add(b);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(authorBooks);
    }


    /**
     * @return The added book
     * @param book indicates the book to be added
     * */
    @Override
    public Optional<Book> save(Book book) {
        try {
            books.add(book);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(book);
    }

    /**
     * @return The updated book
     * @param book indicates the book to be updated
     * @param id indicates the id of the book to be added
     * */
    @Override
    public Optional<Book> update(Book book, Long id) {
        try {
            books.replaceAll(b -> b.getId().equals(id) ? book : b);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(book);
    }

    /**
     * @param id indicates the id of the book to be deleted
     * */
    @Override
    public synchronized void deleteById(Long id) {
        List<Book> itemToBeDeleted = new ArrayList<>();
        try {
            books.forEach(b -> {
                if (b.getId().equals(id)) {
                    itemToBeDeleted.add(b);
                }

            });
            books.remove(itemToBeDeleted.get(0));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id indicates the id of the book to be searched
     * */
    @Override
    public Optional<Book> findById(Long id) {
        List<Book> selectedBook = new ArrayList<>();
        if (books.size() >= id) {
            books.stream().forEach(b -> {
                if (b.getId().equals(id)) {
                    selectedBook.add(b);
                }
            });
            if (selectedBook.size() > 0) {
                return Optional.of(selectedBook.get(0));
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

}
