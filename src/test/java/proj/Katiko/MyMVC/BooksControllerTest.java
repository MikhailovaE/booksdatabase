package proj.Katiko.MyMVC;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;
import proj.Katiko.MyMVC.Base.DataOfBook.Genre;
import proj.Katiko.MyMVC.Base.Repository.AuthorRepository;
import proj.Katiko.MyMVC.Base.Repository.BookRepository;
import proj.Katiko.MyMVC.Controllers.BooksController;
import proj.Katiko.MyMVC.View.Pager;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BooksControllerTest {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BooksController booksController;
    private Model model;
    private Book book;

    @Before
    public void setMock() {
        model = mock(Model.class);
        bookRepository = mock(BookRepository.class);
        authorRepository = mock(AuthorRepository.class);
        booksController = new BooksController(bookRepository, authorRepository);
        configureBook();
    }

    private void configureBook() {
        this.book = new Book();
        book.setId(3);
        book.setName("jugug");
        book.setGenre(Genre.Crime);
        book.setAuthor(new Author("fdg","ghhfg","2018"));
        book.setSize("2423");
        book.setYear("2018");
    }


    @Test
    public void addBookTest() {
        String result = booksController.addBook(
                book.getName(),
                book.getGenreString(),
                book.getAuthor().getId(),
                book.getYear(),
                book.getSize(),
                model
        );
        assertEquals("redirect:/", result);
    }

}
