package controller;

import entity.Author;
import entity.Book;
import entity.ParsedEntity;
import main.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.BookRepository;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    public void chooseOperation(ParsedEntity parsedEntity) {
        Book book;
        switch (parsedEntity.getStatus()) {
            case 0:
                System.out.println("Parsing error");
                break;
            case 1:
                System.out.println("Your books:");
                this.getAll().stream().sorted(Comparator.comparing(o -> o.getName().toUpperCase()))
                        .forEach(System.out::println);
                break;
            case 2:
                book = new Book(parsedEntity.getBookName(), new Author(parsedEntity.getAuthorName()));
                this.add(book);
                break;
            case 3:
                book = new Book(parsedEntity.getBookName());
                String newName = Program.getScanner().nextLine();
                List<Book> edit = findByName(book.getName());

                if (edit.size() == 0) {
                    System.out.println("Nothing to edit");
                } else if (edit.size() == 1) {
                    this.editBook(edit.get(0), newName);

                } else {
                    System.out.println("Choose book to edit :");
                    this.showAll(edit);
                    int num = Program.getScanner().nextInt();

                    this.editBook(edit.get(num - 1), newName);
                }
                break;
            case 4:
                book = new Book(parsedEntity.getBookName());
                List<Book> delete = findByName(book.getName());

                if (delete.size() == 0) {
                    System.out.println("Nothing to delete");
                } else if (delete.size() == 1) {
                    this.deleteBook(delete.get(0));
                } else {
                    System.out.println("Choose book to delete :");
                    this.showAll(delete);
                    int num = Program.getScanner().nextInt();
                    this.deleteBook(delete.get(num - 1));
                }
            case 5:
            default:
        }
    }

    private void showAll(List<Book> edit) {
        final int[] i = {0};
        edit.stream().forEach(book -> {
            System.out.print(++i[0] + " ");
            System.out.println(book);
        });
    }

    public List<Book> getAll() {

        return bookRepository.getAll();
    }

    public Book add(Book book) {
        try {
            Book saveBook = bookRepository.saveBook(book);
            System.out.println("Added " + saveBook);
            return saveBook;
        } catch (Exception e) {
            System.out.println("Something going wrong, maybe such book this author already exist");
        }
        System.out.println("Nothing adding");
        return null;
    }

    public List<Book> findByName(String oldName) {
        return bookRepository.findByBookName(oldName);
    }


    public Book editBook(Book book, String newName) {
        try {
            Book editBook = bookRepository.editBook(book, newName);
            System.out.println("Book name changed from " + book.getName() + " to " + editBook.getName());
            return editBook;
        } catch (Exception e) {
            System.out.println("Something going wrong, maybe such book this author already exist");
        }
        return null;
    }

    public void deleteBook(Book book){
        bookRepository.deleteBook(book);
        System.out.println("Book deleted" + book);

    }
}
