package repository.books;

import entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Stanislav Cheslavskyi on 06.10.2017.
 */
@Transactional(readOnly = true)
public interface CrudAuthorRepository extends JpaRepository<Author,Integer>{

    @Transactional
    Author save(Author author);

    Author getByName(String name);
}
