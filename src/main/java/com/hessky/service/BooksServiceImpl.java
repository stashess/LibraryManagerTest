package com.hessky.service;

import com.hessky.entity.Author;
import com.hessky.entity.Book;
import com.hessky.repository.BookRepository;
import com.hessky.repository.books.CrudAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 21.10.2017.
 */

@Service
@Transactional(readOnly = true)
public class BooksServiceImpl implements BooksService {

    private BookRepository bookRepository;
    private CrudAuthorRepository crudAuthorRepository;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository, CrudAuthorRepository crudAuthorRepository) {
        this.bookRepository = bookRepository;
        this.crudAuthorRepository = crudAuthorRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> findBookByName(String name) {
        return bookRepository.findByBookName(name);
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        try {
            Author byName = crudAuthorRepository.getByName(book.getAuthor().getName());
            if (byName == null)
                crudAuthorRepository.save(book.getAuthor());
            else book.setAuthor(byName);

            Book saveBook = bookRepository.saveBook(book);
            return saveBook;
        } catch (Exception e) {
            System.out.println("Something going wrong, maybe such book this author already exist");
        }
        return null;
    }

    @Override
    @Transactional
    public Book editBook(Book book, String newName) {
        try {
            Book editBook = bookRepository.editBook(book, newName);
            return editBook;
        } catch (Exception e) {
            System.out.println("Something going wrong, maybe such book this author already exist");
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        bookRepository.deleteBook(book);
        System.out.println("Book deleted " + book);
    }
}
