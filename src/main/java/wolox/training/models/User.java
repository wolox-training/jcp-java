package wolox.training.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userUsername;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = true)
    private LocalDate userBirthday;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Book> books;

    public User() {
        this.books = new ArrayList<>();
    }

    public User(Long userId, String UserUsername, String userName, LocalDate userBirthday,
            List<Book> books) {
        this.userUsername = UserUsername;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.books = books;
        this.userId = userId;
    }

    public User(String UserUsername, String userName, LocalDate userBirthday,
            List<Book> books) {
        this.userUsername = UserUsername;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        if (books.stream().anyMatch(b -> b.equals(book))) {
            this.books.remove(book);
        }
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDate getUserBirthday() {
        return userBirthday;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserBirthday(LocalDate userBirthday) {
        this.userBirthday = userBirthday;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
