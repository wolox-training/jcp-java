package wolox.training.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    public Book(long id) {
        this.id = id;
    }

    public Book() {

    }

    public Book(Long id, String genre, String author, String image, String title, String publisher, String year,
            Integer pages, String isbn) {
        this.id = id;
        Genre = genre;
        Author = author;
        Image = image;
        Title = title;
        Publisher = publisher;
        Year = year;
        Pages = pages;
        this.isbn = isbn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true, unique = false)
    private String Genre;

    @Column(nullable = false)
    private String Author;

    @Column(nullable = false)
    private String Image;

    @Column(nullable = false, unique = true)
    private String Title;

    @Column(nullable = false, unique = true)
    private String Subtitle;

    @Column(nullable = false)
    private String Publisher;

    @Column(nullable = false)
    private String Year;

    @Column(nullable = false)
    private Integer Pages; //(Requerido)

    @Column(nullable = false, unique = true)
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return Genre;
    }

    public String getAuthor() {
        return Author;
    }

    public String getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public String getPublisher() {
        return Publisher;
    }

    public String getYear() {
        return Year;
    }

    public Integer getPages() {
        return Pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setPages(Integer pages) {
        Pages = pages;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
