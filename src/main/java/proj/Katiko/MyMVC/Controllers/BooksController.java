package proj.Katiko.MyMVC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;
import proj.Katiko.MyMVC.Base.Repository.BookRepository;

@Controller
public class BooksController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value="/createBook",method=RequestMethod.POST)
    public String addBook(@RequestParam String name,
                           @RequestParam String year,
                           @RequestParam String size,
                           Model model) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setYear(year);
        newBook.setSize(size);
        bookRepository.save(newBook);

        model.addAttribute("book", newBook);
        return "redirect:/book/" + newBook.getId();
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBookPage() {

        return "AddBook";
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public String deleteBook(@PathVariable String name,
                             Model model) {
        return null;
    }

}