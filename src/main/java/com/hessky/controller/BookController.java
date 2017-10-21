package com.hessky.controller;

import com.hessky.Main;
import com.hessky.entity.Author;
import com.hessky.entity.Book;
import com.hessky.service.BooksService;
import com.hessky.to.ParsedEntity;
import com.hessky.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Controller
public class BookController {

    private BooksService booksService;

    @Autowired
    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    public void chooseOperation(ParsedEntity parsedEntity) {
        switch (parsedEntity.getStatus()) {
            case Parser.INPUT_ERROR:
                parsingError();
                break;
            case Parser.GET_All:
                getBooks();
                break;
            case Parser.ADD:
                addBook(parsedEntity);
                break;
            case Parser.EDIT:
                editBook(parsedEntity);
                break;
            case Parser.DELETE:
                deleteBook(parsedEntity);
            case Parser.IGNORE:
            default:
        }
    }

    private void deleteBook(ParsedEntity parsedEntity) {
        List<Book> delete = booksService.findBookByName(parsedEntity.getBookName());

        if (delete.size() == 0) {
            System.out.println("Nothing to delete");
            return;
        } else if (delete.size() == 1) {
            booksService.deleteBook(delete.get(0));
        } else {
            System.out.println("Choose book to delete :");
            showAll(delete);
            int num = Main.getScanner().nextInt();
            booksService.deleteBook(delete.get(num - 1));
        }
    }

    private void editBook(ParsedEntity parsedEntity) {
        System.out.println("Input new name:");
        String newName = Main.getScanner().nextLine();
        List<Book> bookListWithSameNames = booksService.findBookByName(parsedEntity.getBookName());
        Book editedBook = null;
        if (bookListWithSameNames.size() == 0) {
            System.out.println("Nothing to edit");
            return;
        } else if (bookListWithSameNames.size() == 1) {
            editedBook = booksService.editBook(bookListWithSameNames.get(0), newName);
        } else if (bookListWithSameNames.size() > 1) {
            System.out.println("Choose book to edit :");
            showAll(bookListWithSameNames);
            int num = Main.getScanner().nextInt();
            editedBook = booksService.editBook(bookListWithSameNames.get(num - 1), newName);
        }
        if (editedBook != null)
            System.out.println("Book was renamed from " + parsedEntity.getBookName() + " to " + newName);
    }

    private void addBook(ParsedEntity parsedEntity) {
        Book book = new Book(parsedEntity.getBookName(), new Author(parsedEntity.getAuthorName()));
        Book addedBook = booksService.addBook(book);
        if (addedBook == null) {
            System.out.println("Nothing adding");
            return;
        }
        System.out.println("Added " + addedBook);
    }

    private void getBooks() {
        System.out.println("Your books:");
        showAll(booksService.getAll());
    }

    private void parsingError() {
        System.out.println("Parsing error");
    }

    private void showAll(List<Book> bookList) {
        final int[] i = {0};
        bookList.stream().sorted(Comparator.comparing(o -> o.getName().toUpperCase()))
                .forEach(book -> {
                    System.out.print(++i[0] + " ");
                    System.out.println(book);
                });
    }
}
