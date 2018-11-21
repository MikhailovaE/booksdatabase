package proj.Katiko.MyMVC.Base.DataOfBook;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String year;
    private String size;
    @ManyToMany
    private List<Author> author;


    public Book(String name, String year, String size,
                List<Author> author) {
        this.name = name;
        this.year = year;
        this.size = size;
        this.author = author;
    }

    public Book() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public boolean hasAuthor(Author author) {
        for (Author containedAuthor : getAuthor()) {
            if (containedAuthor.getId() == author.getId()) {
                return true;
            }
        }
        return false;

    }
}


