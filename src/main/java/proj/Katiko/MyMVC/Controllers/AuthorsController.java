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
import java.util.List;
import java.util.Optional;

@Controller
public class AuthorsController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 7;

    public AuthorsController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
    public String addAuthor(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String year,
                            Model model) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(firstName);
        newAuthor.setLastName(lastName);
        newAuthor.setYearOfBirth(year);
        authorRepository.save(newAuthor);
        return "redirect:/authorList";
    }

    @RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
    public String addAuthorPage(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "AddAuthor";
    }

    @RequestMapping(value = "/readAuthor", method = RequestMethod.POST)
    public void readAuthor(@PathVariable String firstName,
                           @PathVariable String secondName,
                           Model model) {
    }

    @RequestMapping(value = "/authorList", method = RequestMethod.GET)
    public String getAuthors(@RequestParam("page") Optional<Integer> page,
                             Model model) {

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<Author> authorsPage = authorRepository.findAll(new PageRequest(evalPage, PAGE_SIZE));
        model.addAttribute("authorsPage", authorsPage);
        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("pager", new Pager(authorsPage.getTotalPages(), authorsPage.getNumber(), BUTTONS_TO_SHOW));

        return "AuthorList";
    }

    @RequestMapping(value = "/updateAuthor", method = RequestMethod.GET)
    public String updateAuthorPage(@RequestParam Long id, Model model) {
        model.addAttribute(authorRepository.findById(id).get());
        return "AddAuthor";
    }

    @RequestMapping(value = "/updateAuthor", method = RequestMethod.POST)
    public String updateAuthor(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String year,
                             @RequestParam Long id,
                             Model model) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setYearOfBirth(year);
            authorRepository.save(author);
        }

        return "redirect:/authorList";
    }

    @RequestMapping(value = "/deleteAuthor", method = RequestMethod.DELETE)
    public String deleteAuthor(@RequestParam Long id,
                               Model model) {
        Iterable<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (book.getAuthor().getId() == id) {
                bookRepository.deleteById(book.getId());
            }
        }

        authorRepository.deleteById(id);
        return "redirect:/authorList" ;
    }
}