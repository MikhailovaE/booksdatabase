package proj.Katiko.MyMVC.Base.Repository;

import proj.Katiko.MyMVC.Base.DataOfBook.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

}

