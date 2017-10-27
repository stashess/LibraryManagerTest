package com.hessky.util;

import com.hessky.to.InputData;

import java.util.regex.Pattern;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public class Parser {
    private static Pattern add = Pattern.compile("^add[ ][^\"]+[ ].+\"$");
    private static Pattern edit = Pattern.compile("^edit[ ].+$");
    private static Pattern delete = Pattern.compile("^delete[ ].+$");
    private static Pattern getAll = Pattern.compile("^all[ ]books$");
    private static Pattern ignore = Pattern.compile("");

    public static InputData parse(String s) {
        String book;
        String author;

        if (add.matcher(s).matches()) {
            book = s.substring(s.indexOf('\"') + 1, s.length() - 1);
            author = s.substring(4, s.indexOf('\"') - 1);
            return new InputData(StatusCode.ADD, author, book);
        }

        if (edit.matcher(s).matches()) {
            book = s.substring(s.indexOf(' ') + 1, s.length());
            return new InputData(StatusCode.EDIT, book);
        }

        if (delete.matcher(s).matches()) {
            book = s.substring(s.indexOf(' ') + 1, s.length());
            return new InputData(StatusCode.DELETE, book);
        }

        if (getAll.matcher(s).matches()) {
            return new InputData(StatusCode.GET_All);
        }

        if (ignore.matcher(s).matches()) {
            return new InputData(StatusCode.IGNORE);
        }


        return new InputData(StatusCode.INPUT_ERROR);
    }

}
