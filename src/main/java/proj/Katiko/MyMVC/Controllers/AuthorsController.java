package proj.Katiko.MyMVC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import proj.Katiko.MyMVC.Base.Repository.AuthorRepository;

@Controller
public class AuthorsController {

    @Autowired
     AuthorRepository authorRepository;

    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
    public void addArtist(@PathVariable String firstName,
                          @PathVariable String secondName,
                          @PathVariable String yearOfBirth,
                          Model model) {

    }

    @RequestMapping(value = "/author", method = RequestMethod.GET)
    public String addAuthorPage() {
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

