package vn.fpt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Không được để trống")
    private String nameBook;
    @Min(value = 0)
    private double price;
    @NotBlank(message = "Không được để trống")
    private String availible;
    @NotBlank(message = "Không được để trống")
    private String img;
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @ManyToOne(targetEntity = Catagory.class)
    @JoinColumn(name = "catagory_id", referencedColumnName = "id")
    private Catagory catagory;

    @ManyToMany
    @JoinTable(name = "book_borrow",  joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "borrow_id"))
    private Set<Borrow> borrow;

    public Book() {
    }

    public Book(@NotBlank(message = "Không được để trống") String nameBook, @Min(value = 0) double price, @NotBlank(message = "Không được để trống") String availible, @NotBlank(message = "Không được để trống") String img, Author author, Catagory catagory, Set<Borrow> borrow) {
        this.nameBook = nameBook;
        this.price = price;
        this.availible = availible;
        this.img = img;
        this.author = author;
        this.catagory = catagory;
        this.borrow = borrow;
    }

    public Book(int id, String nameBook, double price, Author author, Catagory catagory) {
        this.id = id;
        this.nameBook = nameBook;
        this.price = price;
        this.author = author;
        this.catagory = catagory;
    }

    public Book(String nameBook, Author author) {
        this.nameBook = nameBook;
        this.author = author;
    }

    public Set<Borrow> getBorrow() {
        return borrow;
    }

    public void setBorrow(Set<Borrow> borrow) {
        this.borrow = borrow;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getAvailible() {
        return availible;
    }

    public void setAvailible(String availible) {
        this.availible = availible;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
