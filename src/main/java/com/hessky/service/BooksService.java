package com.hessky.service;


import com.hessky.entity.Book;

import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 21.10.2017.
 */
public interface BooksService {

    List<Book> getAll();

    Book addBook(Book book);

    List<Book> findBookByName(String name);

    Book editBook(Book book, String newName);

    void deleteBook(Book book);
}
