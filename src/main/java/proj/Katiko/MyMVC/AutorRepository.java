package proj.Katiko.MyMVC;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    public List<Author> findByLabel(String label);
}

