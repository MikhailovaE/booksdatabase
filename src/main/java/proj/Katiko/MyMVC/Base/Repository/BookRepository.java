package proj.Katiko.MyMVC.Base.Repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import proj.Katiko.MyMVC.Base.DataOfBook.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}

