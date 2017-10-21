package com.hessky.entity;

import javax.persistence.*;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Entity
@Table(name = "books")
public class Book {
    @Id
    @SequenceGenerator(name = "global_seq1", sequenceName = "global_seq1", allocationSize = 1, initialValue = 100001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq1")
    @Access(value = AccessType.PROPERTY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book(String name, int id, Author author) {
        this.name = name;
        this.id = id;
        this.author = author;
    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public Book() {
    }

    public Book(String bookName) {
        this.name = bookName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return author + "\"" + name  + "\"";
    }
}
