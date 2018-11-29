package proj.Katiko.MyMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import proj.Katiko.MyMVC.Base.Repository.AuthorRepository;

@Controller
public class AuthorsController {

    private AuthorRepository authorRepository;

    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
    public String addAuthor(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @PathVariable String yearOfBirth,
            Model model) {
        authorRepository.save(new Author(firstName, lastName, yearOfBirth));
        return "redirect:/author";
    }

    @RequestMapping(value = "/addAuthor")
    public String addAuthorPage(Model model) {
        return "AddAuthor";
    }

    @RequestMapping(value = "/readAuthor", method = RequestMethod.POST)
    public void readAuthor(@PathVariable String firstName,
                           @PathVariable String secondName,
                           Model model) {
    }

    @RequestMapping(value = "/authorList", method = RequestMethod.GET)
    public String getAuthors(Model model) {

        model.addAttribute("authors", authorRepository.findAll());
        return "AuthorList";
    }

   /* @RequestMapping(value = "/updateAuthor", method = RequestMethod.POST)
    public void updateAuthor(@PathVariable String firstName,
                             @PathVariable String secondName,
                             @PathVariable String yearOfBirth,
                             Model model) {

    }*/

    @RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST)
    public void deleteAuthor(@PathVariable String firstName,
                             @PathVariable String secondName,
                             Model model) {
    }

}

