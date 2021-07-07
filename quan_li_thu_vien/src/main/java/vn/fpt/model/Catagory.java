package vn.fpt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Không được để trống")
    private String nameCatagory;
    @OneToMany(mappedBy = "catagory")
    Set<Book> books;

    public Catagory() {
    }

    public Catagory(int id, String nameCatagory, Set<Book> books) {
        this.id = id;
        this.nameCatagory = nameCatagory;
        this.books = books;
    }

    public Catagory(String nameCatagory, Set<Book> books) {
        this.nameCatagory = nameCatagory;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCatagory() {
        return nameCatagory;
    }

    public void setNameCatagory(String nameCatagory) {
        this.nameCatagory = nameCatagory;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
