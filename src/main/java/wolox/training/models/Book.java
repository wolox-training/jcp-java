package wolox.training.models;

import static wolox.training.constants.ErrorConstants.nullMessage;

import com.google.common.base.Preconditions;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = true, unique = false)
    private String genre;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String subtitle;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false, unique = true)
    private String isbn;

    public Book() {
    }

    public Book(String genre, String author, String image, String title,
            String subtitle, String publisher, String year, Integer pages, String isbn) {

        this.genre = genre;
        this.author = author;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getYear() {
        return year;
    }

    public Integer getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        Preconditions.checkNotNull(author, nullMessage.replace("{object}", author));
        this.author = author;
    }

    public void setImage(String image) {
        Preconditions.checkNotNull(image, nullMessage.replace("{object}", image));
        this.image = image;
    }

    public void setTitle(String title) {
        Preconditions.checkNotNull(title, nullMessage.replace("{object}", title));
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        Preconditions.checkNotNull(subtitle, nullMessage.replace("{object}", subtitle));
        this.subtitle = subtitle;
    }

    public void setPublisher(String publisher) {
        Preconditions.checkNotNull(publisher, nullMessage.replace("{object}", publisher));
        this.publisher = publisher;
    }

    public void setYear(String year) {
        Preconditions.checkNotNull(year, nullMessage.replace("{object}", year));
        this.year = year;
    }

    public void setPages(Integer pages) {
        Preconditions.checkNotNull(pages, nullMessage.replace("{object}", String.valueOf(pages)));
        this.pages = pages;
    }

    public void setIsbn(String isbn) {
        Preconditions.checkNotNull(isbn, nullMessage.replace("{object}", isbn));
        this.isbn = isbn;
    }
}
