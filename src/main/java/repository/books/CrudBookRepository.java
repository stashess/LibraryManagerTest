package repository.books;

import entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Transactional(readOnly = true)
public interface CrudBookRepository extends JpaRepository<Book , Integer> {

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAll();

    @Query("SELECT b FROM Book b JOIN FETCH b.author WHERE b.name=?1")
    List<Book> findByName(String name);

    Book save(Book book);

    @Override
    void delete(Book book);

}
