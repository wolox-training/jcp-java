package wolox.training.models;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String user_username;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private LocalDate user_birthday;

    @OneToMany(mappedBy = "users")
    private List<Book> books;

    public Users() {
    }

    public Users(Long user_id, String user_username, String user_name, LocalDate user_birthday,
            List<Book> books) {
        this.user_username = user_username;
        this.user_name = user_name;
        this.user_birthday = user_birthday;
        this.books = books;
        this.user_id = user_id;
    }

    public Users(String user_username, String user_name, LocalDate user_birthday,
            List<Book> books) {
        this.user_username = user_username;
        this.user_name = user_name;
        this.user_birthday = user_birthday;
        this.books = books;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public String getUser_name() {
        return user_name;
    }

    public LocalDate getUser_birthday() {
        return user_birthday;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_birthday(LocalDate user_birthday) {
        this.user_birthday = user_birthday;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
