package proj.Katiko.MyMVC.Controllers;

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
import proj.Katiko.MyMVC.View.Pager;

import java.util.Optional;

@Controller
public class BooksController {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 7;


    public BooksController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root (@RequestParam("page") Optional<Integer> page,
                        Model model)
    {
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Book> booksPage = bookRepository.findAll(new PageRequest(evalPage, PAGE_SIZE));
        model.addAttribute("booksPage", booksPage);
        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("pager", new Pager(booksPage.getTotalPages(), booksPage.getNumber(), BUTTONS_TO_SHOW));

        return "Title";
    }

    @RequestMapping (value = "/title",method = RequestMethod.GET)
    public String title (Model model)
    {
        return "redirect:/";
    }

    @RequestMapping(value="/createBook",method=RequestMethod.POST)
    public String addBook(@RequestParam String name,
                          @RequestParam String gen,
                          @RequestParam Long auth,
                          @RequestParam String year,
                          @RequestParam String size,
                          Model model) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setGenre(Genre.valueOf(gen));

        Optional<Author> author = authorRepository.findById(auth);
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

    @RequestMapping(value = "/updateBook", method = RequestMethod.GET)
    public String updateBookPage(@RequestParam Long id,
                                Model model) {
        model.addAttribute("book", bookRepository.findById(id).get());
        model.addAttribute("authors", authorRepository.findAll());
        return "AddBook";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(@RequestParam String name,
                               @RequestParam String gen,
                               @RequestParam Long auth,
                               @RequestParam String year,
                               @RequestParam String size,
                               @RequestParam Long id,
                               Model model) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(name);
            book.setGenre(Genre.valueOf(gen));
            Optional<Author> authorOptional = authorRepository.findById(auth);
            if (authorOptional.isPresent()) {
                book.setAuthor(authorOptional.get());
            }
            book.setYear(year);
            book.setSize(size);
            bookRepository.save(book);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/viewBook", method = RequestMethod.GET)
    public String BookPage(@RequestParam Long id,
                           Model model) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "Book";
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE)
    public String deleteBook(@RequestParam Long idBook,
                             Model model)
    {
        bookRepository.deleteById(idBook);
        return "redirect:/";
    }

}