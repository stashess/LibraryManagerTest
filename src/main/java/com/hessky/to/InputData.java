package com.hessky.to;

import com.hessky.util.StatusCode;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
public class InputData {
    private final StatusCode status;
    private final String authorName;
    private final String bookName;

    public InputData(StatusCode status, String authorName, String bookName) {
        this.status = status;
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public InputData(StatusCode status) {
        this.status = status;
        authorName = null;
        bookName = null;
    }

    public InputData(StatusCode status, String bookName) {
        this.status = status;
        this.bookName = bookName;
        authorName = null;
    }

    public StatusCode getStatus() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputData inputData = (InputData) o;

        if (status != inputData.status) return false;
        if (authorName != null ? !authorName.equals(inputData.authorName) : inputData.authorName != null) return false;
        return bookName != null ? bookName.equals(inputData.bookName) : inputData.bookName == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "status=" + status +
                ", authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
