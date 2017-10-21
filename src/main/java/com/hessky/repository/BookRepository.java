package com.hessky.repository;


import com.hessky.entity.Book;

import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public interface BookRepository {
    List<Book> getAll();

    Book saveBook(Book book) throws Exception;

    void deleteBook(Book book);

    List<Book> findByBookName(String name);

    Book editBook(Book book, String name) throws Exception;

}
