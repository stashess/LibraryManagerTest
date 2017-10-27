package com.hessky.controller;

import com.hessky.Main;
import com.hessky.entity.Author;
import com.hessky.entity.Book;
import com.hessky.service.BooksService;
import com.hessky.to.InputData;
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

    public void chooseOperation(InputData inputData) {
        switch (inputData.getStatus()) {
            case INPUT_ERROR:
                parsingError();
                break;
            case GET_All:
                getBooks();
                break;
            case ADD:
                addBook(inputData);
                break;
            case EDIT:
                editBook(inputData);
                break;
            case DELETE:
                deleteBook(inputData);
            case IGNORE:
            default:
        }
    }

    private void deleteBook(InputData inputData) {
        List<Book> delete = booksService.findBookByName(inputData.getBookName());

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

    private void editBook(InputData inputData) {
        System.out.println("Input new name:");
        String newName = Main.getScanner().nextLine();
        List<Book> bookListWithSameNames = booksService.findBookByName(inputData.getBookName());
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
            System.out.println("Book was renamed from " + inputData.getBookName() + " to " + newName);
    }

    private void addBook(InputData inputData) {
        Book book = new Book(inputData.getBookName(), new Author(inputData.getAuthorName()));
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
