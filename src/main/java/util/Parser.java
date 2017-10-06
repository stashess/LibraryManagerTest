package util;

import entity.ParsedEntity;

import java.util.regex.Pattern;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public class Parser {
    public final static int INPUT_ERROR = 0;
    public final static int GET_All = 1;
    public final static int ADD = 2;
    public final static int EDIT = 3;
    public final static int DELETE = 4;
    public static final int IGNORE = 5;

    public static ParsedEntity parse(String s) {
        String book;
        String author;

        Pattern add = Pattern.compile("^add[ ][^\"]+[ ].+\"$");
        if (add.matcher(s).matches()) {
            book = s.substring(s.indexOf('\"') + 1, s.length() - 1);
            author = s.substring(4, s.indexOf('\"') - 1);
            return new ParsedEntity(Parser.ADD, author, book);
        }

        Pattern edit = Pattern.compile("^edit[ ].+$");
        if (edit.matcher(s).matches()) {
            book = s.substring(s.indexOf(' ') + 1, s.length());
            return new ParsedEntity(Parser.EDIT, book);
        }

        Pattern delete = Pattern.compile("^delete[ ].+$");
        if (delete.matcher(s).matches()) {
            book = s.substring(s.indexOf(' ') + 1, s.length());
            return new ParsedEntity(Parser.DELETE, book);
        }

        Pattern getAll = Pattern.compile("^all[ ]books$");
        if (getAll.matcher(s).matches()) {
            return new ParsedEntity(Parser.GET_All);
        }

        Pattern ignore = Pattern.compile("");
        if (ignore.matcher(s).matches()) {
            return new ParsedEntity(Parser.IGNORE);
        }


        return new ParsedEntity(Parser.INPUT_ERROR);
    }

}
