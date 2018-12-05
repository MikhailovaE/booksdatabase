package proj.Katiko.MyMVC.Base.DataOfBook;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idBook;
    private String bookName;
    private String year;
    private String size;

    @ManyToOne
    @JoinColumn(name = "id")
    private Author author;

    @Column
    @Enumerated(EnumType.STRING)
    private Genre genre;


    public Book(String name, String year, String size,
                Author author, Genre genre) {
        this.bookName = name;
        this.year = year;
        this.size = size;
        this.author = author;
        this.genre = genre;
    }

    public Book() {

    }

    public long getId() {
        return idBook;
    }

    public void setId(long id) {
        this.idBook = id;
    }

    public String getName() {
        return bookName;
    }

    public void setName(String name) {
        this.bookName = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() { return genre; }

    public void setGenre(Genre genre) { this.genre = genre; }

    public String getGenreString() {
        return this.genre.name();
    }

/*
    public boolean hasAuthor(Author author) {
        for (Author containedAuthor : getAuthor()) {
            if (containedAuthor.getId() == author.getId()) {
                return true;
            }
        }
        return false;
    }
*/
}


