package wolox.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long book_id;

    @Column(name = "user_id", nullable = true, unique = false)
    private Long user_id;

    @Column(name = "book_genre", nullable = true, unique = false)
    private String book_genre;

    @Column(name = "book_author", nullable = false)
    private String book_author;

    @Column(name = "book_image", nullable = false)
    private String book_image;

    @Column(name = "book_title", nullable = false, unique = true)
    private String book_title;

    @Column(name = "book_subtitle", nullable = false, unique = true)
    private String book_subtitle;

    @Column(name = "book_publisher", nullable = false)
    private String book_publisher;

    @Column(name = "book_year", nullable = false)
    private String book_year;

    @Column(name = "book_pages", nullable = false)
    private Integer book_pages;

    @Column(name = "book_isbn", nullable = false, unique = true)
    private String book_isbn;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Users users;


    public Book() {

    }

    public Book(Long book_id, Long user_id, String book_genre, String book_author, String book_image, String book_title,
            String book_subtitle, String book_publisher, String book_year, Integer book_pages, String book_isbn) {

        this.book_id = book_id;
        this.book_genre = book_genre;
        this.book_author = book_author;
        this.book_image = book_image;
        this.book_title = book_title;
        this.book_subtitle = book_subtitle;
        this.book_publisher = book_publisher;
        this.book_year = book_year;
        this.book_pages = book_pages;
        this.book_isbn = book_isbn;
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public String getBook_genre() {
        return book_genre;
    }

    public String getBook_author() {
        return book_author;
    }

    public String getBook_image() {
        return book_image;
    }

    public String getBook_title() {
        return book_title;
    }

    public String getBook_subtitle() {
        return book_subtitle;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public String getBook_year() {
        return book_year;
    }

    public Integer getBook_pages() {
        return book_pages;
    }

    public String getBook_isbn() {
        return book_isbn;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public void setId(long id) {
        this.book_id = id;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public void setBook_subtitle(String book_subtitle) {
        this.book_subtitle = book_subtitle;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public void setBook_year(String book_year) {
        this.book_year = book_year;
    }

    public void setBook_pages(Integer book_pages) {
        this.book_pages = book_pages;
    }

    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }
}
