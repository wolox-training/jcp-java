package wolox.training.controllers;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private static final String DATE_PATTERN = "yyyy-MM-dd";


    public UserController(IUserRepository userRepository, IBookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * @return all the users contained in the model
     */

    @GetMapping()
    public Iterable<User> findAll(@RequestParam Integer page, @RequestParam Integer count,
            @RequestParam String sorting) {
        return userRepository.findAll(page, count, sorting, PageRequest.of(page, count).withSort(Sort.by(sorting)));
    }

    /**
     * @param id indicates the id to be searched
     *
     * @return the entire user of the selected id
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(value = "id") Long id) {
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

    @RequestMapping(method = RequestMethod.GET, params = {"fromDate", "toDate", "name"})
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByNameAndBirthdayBetween(
            @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) LocalDate fromDate,
            @RequestParam @DateTimeFormat(pattern = DATE_PATTERN) LocalDate toDate, @RequestParam String name) {
        List<User> users = new ArrayList<>();
        if (userRepository.findByBirthdayBetweenAndNameContaining(fromDate, toDate, name).get().iterator().hasNext()) {
            userRepository.findByBirthdayBetweenAndNameContaining(fromDate, toDate, name).get()
                    .forEach(u -> users.add(u));
        }

        return users;
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
    public void deleteUserById(@PathVariable(value = "id") Long id) {
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
