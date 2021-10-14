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
    private Long bookId;

    @Column(nullable = true, unique = false)
    private String bookGenre;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(nullable = false)
    private String bookImage;

    @Column(nullable = false, unique = true)
    private String bookTitle;

    @Column(nullable = false, unique = true)
    private String bookSubtitle;

    @Column(nullable = false)
    private String bookPublisher;

    @Column(nullable = false)
    private String bookYear;

    @Column(nullable = false)
    private Integer bookPages;

    @Column(nullable = false, unique = true)
    private String bookIsbn;

    public Book() {
    }

    public Book(Long bookId, String bookGenre, String bookAuthor, String bookImage, String bookTitle,
            String bookSubtitle, String bookPublisher, String bookYear, Integer bookPages, String bookIsbn) {

        this.bookId = bookId;
        this.bookGenre = bookGenre;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookTitle = bookTitle;
        this.bookSubtitle = bookSubtitle;
        this.bookPublisher = bookPublisher;
        this.bookYear = bookYear;
        this.bookPages = bookPages;
        this.bookIsbn = bookIsbn;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookSubtitle() {
        return bookSubtitle;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public String getBookYear() {
        return bookYear;
    }

    public Integer getBookPages() {
        return bookPages;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookGenre(String bookGenre) {
        Preconditions.checkNotNull(bookGenre, nullMessage);
        this.bookGenre = bookGenre;
    }

    public void setBookAuthor(String bookAuthor) {
        Preconditions.checkNotNull(bookAuthor, nullMessage);
        this.bookAuthor = bookAuthor;
    }

    public void setBookImage(String bookImage) {
        Preconditions.checkNotNull(bookImage, nullMessage);
        this.bookImage = bookImage;
    }

    public void setBookTitle(String bookTitle) {
        Preconditions.checkNotNull(bookTitle, nullMessage);
        this.bookTitle = bookTitle;
    }

    public void setBookSubtitle(String bookSubtitle) {
        Preconditions.checkNotNull(bookSubtitle, nullMessage);
        this.bookSubtitle = bookSubtitle;
    }

    public void setBookPublisher(String bookPublisher) {
        Preconditions.checkNotNull(bookPublisher, nullMessage);
        this.bookPublisher = bookPublisher;
    }

    public void setBookYear(String bookYear) {
        Preconditions.checkNotNull(bookYear, nullMessage);
        this.bookYear = bookYear;
    }

    public void setBookPages(Integer bookPages) {
        Preconditions.checkNotNull(bookPages, nullMessage);
        this.bookPages = bookPages;
    }

    public void setBookIsbn(String bookIsbn) {
        Preconditions.checkNotNull(bookIsbn, nullMessage);
        this.bookIsbn = bookIsbn;
    }
}
