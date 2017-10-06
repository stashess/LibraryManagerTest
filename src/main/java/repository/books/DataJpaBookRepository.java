package repository.books;

import entity.Author;
import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.BookRepository;

import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Repository
@Transactional
public class DataJpaBookRepository implements BookRepository {

    @Autowired
    CrudBookRepository crudBookRepository;

    @Autowired
    CrudAuthorRepository crudAuthorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return crudBookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) throws Exception {
        Author byName = crudAuthorRepository.getByName(book.getAuthor().getName());

        if (byName == null)
            crudAuthorRepository.save(book.getAuthor());
        else book.setAuthor(byName);

        return crudBookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByBookName(String oldname) {
        List<Book> bookList = crudBookRepository.findByName(oldname);
        return bookList;
    }

    @Override
    public Book editBook(Book book, String name) throws Exception{
        book.setName(name);
        return crudBookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        crudBookRepository.delete(book);
    }


}


