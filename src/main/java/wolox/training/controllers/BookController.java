package wolox.training.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.IBookRepository;


/**
 * This class is the service responsible of handling the requests related to the book api
 */

@RestController
@RequestMapping("/books")
public class BookController {

    private final IBookRepository bookRepository;

    public BookController(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Poli") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    /**
     * @return all the books contained in the model
     */
    @GetMapping()
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * @param author indicates the author to be searched
     *
     * @return the entire book of the selected author
     */
    @GetMapping("/author/")
    public List<Book> findByAuthor(@RequestParam String author) {
        List<Book> authorBooks = new ArrayList<>();
        if (bookRepository.findByAuthor(author).get().iterator().hasNext()) {
            bookRepository.findByAuthor(author).get().forEach(b -> authorBooks.add(b));
        }
        return authorBooks;
    }

    @GetMapping("/genre/")
    public List<Book> findByGenre(@RequestParam String genre) {
        List<Book> genreBooks = new ArrayList<>();
        if (bookRepository.findByGenre(genre).get().iterator().hasNext()) {
            bookRepository.findByGenre(genre).get().forEach(b -> genreBooks.add(b));
        }

        return genreBooks;
    }

    /**
     * @param book indicates the book to be added
     *
     * @return the created book
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /**
     * @param id indicates the id of the book to be searched
     *
     * @throws BookNotFoundException returns an exception if the book is not found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    /**
     * @param book indicates the information of the book to be updated
     *
     * @return the updated book
     * @throws BookNotFoundException returns an exception if the book is not found
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@RequestBody Book book) {
        bookRepository.findById(book.getId())
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}
