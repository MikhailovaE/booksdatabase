package com.spbu.math.booksdatabase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository repository;

    @Autowired
    GenreRepository genreRepository;

    @RequestMapping("/author/{id}")
    public String author(@PathVariable Long id, Model model) {
        model.addAttribute("author", repository.findOne(id));
        model.addAttribute("genre", genreRepository.findAll());
        return "author";
    }

    @RequestMapping(value="/author",method=RequestMethod.GET)
    public String authorList(Model model) {
        model.addAttribute("author", repository.findAll());
        return "authors";
    }

    @RequestMapping(value="/author",method=RequestMethod.POST)
    public String authorsAdd(@RequestParam String email,
                                @RequestParam String firstName, @RequestParam String lastName, Model model) {
        Author newAuthor = new Author();
        newAuthor.setEmail(email);
        newAuthor.setFirstName(firstName);
        newAuthor.setLastName(lastName);
        repository.save(newAuthor);

        model.addAttribute("author", newAuthor);
        model.addAttribute("genre", genreRepository.findAll());
        return "redirect:/author/" + newAuthor.getId();
    }

    @RequestMapping(value="/author/{id}/genre", method=RequestMethod.POST)
    public String AuthorAddGenre(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        Genre genre = genreRepository.findOne(genreId);
        Author author = repository.findOne(id);

        if (author != null) {
            if (!author.hasGenre(genre)) {
                genre.getGenre().add(genre);
            }
            repository.save(author);
            model.addAttribute("author", repository.findOne(id));
            model.addAttribute("genre", genreRepository.findAll());
            return "redirect:/author/" + author.getId();
        }

        model.addAttribute("author", repository.findAll());
        return "redirect:/author";
    }

}