package proj.Katiko.MyMVC.Base.Repository;

import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

