package proj.Katiko.MyMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import proj.Katiko.MyMVC.Base.Repository.AuthorRepository;

@RestController
public class AuthorsController {

     private AuthorRepository authorRepository;

    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
    public String addAuthor(
                          @PathVariable String firstName,
                          @PathVariable String lastName,
                          @PathVariable String yearOfBirth,
                          Model model) {
        authorRepository.save(new Author(firstName, lastName, yearOfBirth));
        return "redirect:/author";
    }

    @RequestMapping(value = "/author")
    public String addAuthorPage(Model model)
    {
        return "AddAuthor";
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

    @RequestMapping(value = "/getAuthorByName", method = RequestMethod.POST)
    public void getAuthorByName(@PathVariable String firstName,
                                @PathVariable String secondName,
                                Model model) {}
}

