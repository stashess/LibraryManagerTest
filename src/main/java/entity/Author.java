package entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private int id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Book> books;

    public Author(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public Author(Author a){
        this(a.getName(),a.id);
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

    @Override
    public String toString() {
        return name + " ";

    }
}
