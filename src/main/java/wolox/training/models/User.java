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
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private LocalDate birthday;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Book> books;

    public User() {
        this.books = new ArrayList<>();
    }

    public User(Long id, String username, String name, LocalDate birthday,
            List<Book> books) {
        this.username = username;
        this.name = name;
        this.birthday = birthday;
        this.books = books;
        this.id = id;
    }

    public User(String username, String name, LocalDate birthday,
            List<Book> books) {
        this.username = username;
        this.name = name;
        this.birthday = birthday;
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

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
