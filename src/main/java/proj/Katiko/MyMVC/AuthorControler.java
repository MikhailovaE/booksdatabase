package proj.Katiko.MyMVC;

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
    BookRepository repository;

    // @Autowired
    // AuthorRepository authorRepository;

    @RequestMapping("/book/{id}")
    public String book(@PathVariable Long id, Model model) {
        model.addAttribute("book", repository.findById(id));
        return "book";
    }

    @RequestMapping(value="/book",method=RequestMethod.GET)
    public String bookList(Model model) {
        model.addAttribute("book", repository.findAll());
        return "book";
    }

    @RequestMapping(value="/book",method=RequestMethod.POST)
    public String booksAdd(@RequestParam String name,
                           @RequestParam String year, @RequestParam String size, Model model) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setYear(year);
        newBook.setSize(size);
        repository.save(newBook);

        model.addAttribute("book", newBook);
        return "redirect:/book/" + newBook.getId();
    }
}