package main;

import controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.Parser;

import java.util.Scanner;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
@Component
public class Program {

    static String userInput;
    static Scanner scanner;

    @Autowired
    BookController bookController;

    public static Scanner getScanner() {
        return scanner;
    }

    public void run() {
        scanner = new Scanner(System.in);
        System.out.println("Library manager started\n" +
                "Commands : \n" +
                "add {authorName} {\"bookName\"}\n"+
                "all books\n" +
                "edit {bookName}\n" +
                "newName\n" +
                "delete {bookName} :");
        while (true) {
            userInput = scanner.nextLine();
            bookController.chooseOperation(Parser.parse(userInput));
        }
    }
}
