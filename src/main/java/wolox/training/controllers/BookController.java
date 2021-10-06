package wolox.training.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private IBookRepository bookRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Poli") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    /**
     * @return all the books contained in the model
     */
    @GetMapping("/all")
    public Optional<List<Book>> findAll() {
        List<Book> allBooks = new ArrayList<>();
        if (bookRepository.findAll().iterator().hasNext()) {
            bookRepository.findAll().forEach(b -> allBooks.add(b));
            return Optional.of(allBooks);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param bookAuthor indicates the author to be searched
     *
     * @return the entire book of the selected author
     */
    @GetMapping("/author/{bookAuthor}")
    public Optional<List<Book>> findByAuthor(@PathVariable String bookAuthor) {
        List<Book> authorBooks = new ArrayList<>();
        if (bookRepository.findByBookAuthor(bookAuthor).iterator().hasNext()) {
            bookRepository.findByBookAuthor(bookAuthor).forEach(b -> authorBooks.add(b));
            return Optional.of(authorBooks);
        } else {
            return Optional.empty();
        }
    }

    @GetMapping("/{bookGenre}")
    public Optional<List<Book>> findByGenre(@PathVariable String bookGenre) {
        List<Book> genreBooks = new ArrayList<>();
        if (bookRepository.findByBookGenre(bookGenre).iterator().hasNext()) {
            bookRepository.findByBookGenre(bookGenre).forEach(b -> genreBooks.add(b));
            return Optional.of(genreBooks);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param book indicates the book to be added
     *
     * @return the created book
     */
    @PostMapping("/create")
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
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    /**
     * @param id   indicates the id of the book to be updated
     * @param book indicates the information of the book to be updated
     *
     * @return the updated book
     * @throws BookNotFoundException returns an exception if the book is not found
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}
