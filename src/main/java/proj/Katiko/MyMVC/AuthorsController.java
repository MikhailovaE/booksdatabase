package proj.Katiko.MyMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorsController {

    @Autowired
    private AuthorRepository repository;

    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
    public void addArtist(@PathVariable String firstName,
                          @PathVariable String secondName,
                          @PathVariable String yearOfBirth,
                          Model model) {

    }

    @RequestMapping(value = "/updateArtist", method = RequestMethod.POST)
    public void updateArtist(@PathVariable String firstName,
                             @PathVariable String secondName,
                             @PathVariable String yearOfBirth,
                             Model model) {

    }

    @RequestMapping(value = "/deleteArtist", method = RequestMethod.POST)
    public void deleteArtist(@PathVariable String firstName,
                             @PathVariable String secondName,
                             Model model) {

    }

    @RequestMapping(value = "/getArtistByName", method = RequestMethod.POST)
    public void getArtistByName(@PathVariable String firstName,
                                @PathVariable String secondName,
                                Model model) {}
}

