package entity;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public class ParsedEntity {
    private final int status;
    private final String authorName;
    private final String bookName;

    public ParsedEntity(int status, String authorName, String bookName) {
        this.status = status;
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public ParsedEntity(int status) {
        this.status = status;
        authorName = null;
        bookName = null;
    }

    public ParsedEntity(int status, String bookName) {
        this.status = status;
        this.bookName = bookName;
        authorName = null;
    }

    public int getStatus() {
        return status;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }



    @Override
    public String toString() {
        return "ParsedEntity{" +
                "status=" + status +
                ", authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
