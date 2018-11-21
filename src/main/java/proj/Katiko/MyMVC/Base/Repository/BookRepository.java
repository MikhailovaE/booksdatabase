package proj.Katiko.MyMVC.Base.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}

