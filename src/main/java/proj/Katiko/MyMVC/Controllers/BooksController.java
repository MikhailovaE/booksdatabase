package proj.Katiko.MyMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;
import proj.Katiko.MyMVC.Base.Repository.AuthorRepository;
import proj.Katiko.MyMVC.Base.Repository.BookRepository;

import javax.swing.text.SimpleAttributeSet;
import java.util.Optional;

@Controller
public class BooksController {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10};


    public BooksController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root (Model model)
    {
        model.addAttribute("books",bookRepository.findAll());
        return "Title";
    }

    @RequestMapping (value = "/title",method = RequestMethod.GET)
    public String title (Model model)
    {
        return "redirect:/";
    }

    @RequestMapping(value="/createBook",method=RequestMethod.POST)
    public String addBook(@RequestParam String name,
                          @RequestParam String auth,
                          @RequestParam String year,
                          @RequestParam String size,
                          Model model) {
        Book newBook = new Book();
        newBook.setName(name);

        Optional<Author> author = authorRepository.findById(Long.parseLong(auth));
        if (author.isPresent()) {
            newBook.setAuthor(author.get());
        }

        newBook.setYear(year);
        newBook.setSize(size);
        bookRepository.save(newBook);

        model.addAttribute("books", newBook);
        return "redirect:/";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBookPage(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "AddBook";
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE)
    public String deleteBook(@RequestParam Long idBook,
                             Model model)
    {
        bookRepository.deleteById(idBook);
        return "redirect:/";
    }

}