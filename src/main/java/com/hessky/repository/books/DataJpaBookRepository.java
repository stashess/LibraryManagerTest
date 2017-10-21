package com.hessky.repository.books;

import com.hessky.entity.Book;
import com.hessky.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Repository
public class DataJpaBookRepository implements BookRepository {

    private CrudBookRepository crudBookRepository;

    @Autowired
    public DataJpaBookRepository(CrudBookRepository crudBookRepository) {
        this.crudBookRepository = crudBookRepository;
    }

    @Override
    public List<Book> getAll() {
        return crudBookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) throws Exception {
        return crudBookRepository.save(book);
    }

    @Override
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


