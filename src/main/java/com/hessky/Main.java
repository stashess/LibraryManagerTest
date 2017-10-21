package com.hessky;

import com.hessky.controller.BookController;
import com.hessky.util.Parser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Scanner;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public class Main {

    private static String userInput;
    private static Scanner scanner =  new Scanner(System.in);;
    private static BookController bookController;

    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("spring/spring-app.xml");
        Object controller = context.getBean("bookController");
        if (!(controller instanceof BookController)){
            System.out.println("Something going wrong with context");
            return;
        }
        bookController = (BookController) controller;
        start();
    }

    private static void start() {
        showStartupInfo();
        while (true) {
            userInput = scanner.nextLine();
            bookController.chooseOperation(Parser.parse(userInput));
        }
    }

    private static void showStartupInfo() {
        System.out.println("Library manager started\n" +
                "Commands : \n" +
                "add {authorName} {\"bookName\"}\n"+
                "all books\n" +
                "edit {bookName}\n" +
                "newName\n" +
                "delete {bookName} :");
    }

    public static Scanner getScanner() {
        return scanner;
    }

}
