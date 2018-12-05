package proj.Katiko.MyMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;
import proj.Katiko.MyMVC.Base.DataOfBook.Genre;
import proj.Katiko.MyMVC.Base.Repository.AuthorRepository;

import java.util.Optional;

@Controller
public class AuthorsController {

    private AuthorRepository authorRepository;

    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
    public String addArtist(@ModelAttribute Author author) {
        //TODO тут должно быть сообщение об ошибке
        authorRepository.save(author);
        return "redirect:/authorList";
    }

   /* public String addAuthor(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String yearOfBirth,
                            Model model) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(firstName);
        newAuthor.setLastName(lastName);
        newAuthor.setYearOfBirth(yearOfBirth);
        authorRepository.save(newAuthor);

        model.addAttribute("authors", newAuthor);
        return "redirect:/author";
    }*/

    @RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
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

    @RequestMapping(value = "/updateAuthor", method = RequestMethod.POST)
    public void updateAuthor(@PathVariable String firstName,
                             @PathVariable String secondName,
                             @PathVariable String yearOfBirth,
                             Model model) {

    }

    @RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST)
    public String deleteAuthor(@RequestParam Long id,
                               Model model) {
        authorRepository.deleteById(id);
        return "redirect:/authorList" ;
    }

}

