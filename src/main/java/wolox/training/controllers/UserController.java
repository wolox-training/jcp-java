package wolox.training.controllers;

import static java.util.Objects.isNull;

import java.util.concurrent.atomic.AtomicReference;
import org.springframework.http.HttpStatus;
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
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.IBookRepository;
import wolox.training.repositories.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;


    public UserController(IUserRepository userRepository, IBookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * @return all the users contained in the model
     */
    @GetMapping()
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @param id indicates the id to be searched
     *
     * @return the entire user of the selected id
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@RequestParam Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * @param username indicates the username to be searched
     *
     * @return the entire user of the selected username
     */
    @GetMapping("/getUser/")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * @param user indicates the user to be added
     *
     * @return the created user
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * @param id indicates the id of the user to be searched
     *
     * @throws UserNotFoundException returns an exception if the user is not found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    /**
     * @param user indicates the information of the user to be updated
     *
     * @return the updated user
     * @throws UserNotFoundException returns an exception if the user is not found
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user) {
        userRepository.findById(user.getId())
                .orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }

    /**
     * @param book   indicates the book of the user to be added
     * @param userId indicates the id of the user to be updated
     *
     * @return the updated user
     * @throws UserNotFoundException returns an exception if the user is not found
     */
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User addBookToUser(@RequestBody Book book, @PathVariable Long userId) {
        AtomicReference<Book> existingBook = null;
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.getBooks().stream().forEach(u -> {
                    if (u.getId().equals(book.getId())) {
                        existingBook.set(book);
                    }
                }
        );

        if (isNull(existingBook)) {
            user.addBook(book);
        }
        return userRepository.save(user);
    }

    /**
     * @param book   indicates the book of the user to be removed
     * @param userId indicates the id of the user to be updated
     *
     * @return the updated user
     * @throws UserNotFoundException returns an exception if the user is not found
     */
    @DeleteMapping("/{userId}/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteBookFromUser(@RequestBody Book book, @PathVariable Long userId, @PathVariable Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        AtomicReference<Book> bookToBeDeleted = null;

        user.getBooks().stream().forEach(u -> {
                    if (u.getId().equals(bookRepository.findById(bookId).get())) {
                        bookToBeDeleted.set(book);
                    }
                }
        );

        if (!isNull(bookToBeDeleted)) {
            user.removeBook(bookToBeDeleted.get());
        }

        return userRepository.save(user);
    }

}
