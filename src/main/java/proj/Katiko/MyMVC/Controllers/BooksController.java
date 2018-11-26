package proj.Katiko.MyMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;
import proj.Katiko.MyMVC.Base.Repository.BookRepository;

@Controller
public class BooksController {


    private BookRepository bookRepository;

    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @RequestMapping (value = "/title",method = RequestMethod.GET)
      public String title (){
        return "Title";
    }

    @RequestMapping(value="/createBook",method=RequestMethod.POST)
    public String addBook(@ModelAttribute String name,
                           @RequestParam String year,
                           @RequestParam String size,
                           Model model) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setYear(year);
        newBook.setSize(size);
        bookRepository.save(newBook);

        model.addAttribute("book", newBook);
        return "redirect:/book/";
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