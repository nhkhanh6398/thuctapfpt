package vn.fpt.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.List;
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

    @Min(0)
    private int quantity;
    @NotBlank(message = "Không được để trống")
    private String img;
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @ManyToOne(targetEntity = Catagory.class)
    @JoinColumn(name = "catagory_id", referencedColumnName = "id")
    private Catagory catagory;
    @OneToMany(mappedBy = "books",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CodeBook> codeBooks = new LinkedHashSet<>();
    @ManyToMany
    @JoinTable(name = "borrow_book", joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_account"))
    private List<AccountMember> accountMembers;


    public void generateCode(CodeBook code) {
        codeBooks.add(code);
    }

    public void borrow() {
        if (quantity > 0) quantity--;
    }

    public void returnBook() {
        quantity++;
    }
    public Book() {
    }

    public Book(int id, String nameBook, double price, int quantity,  String img, Author author, Catagory catagory, Set<CodeBook> codeBooks) {
        this.id = id;
        this.nameBook = nameBook;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.author = author;
        this.catagory = catagory;
        this.codeBooks = codeBooks;
    }

    public Book(String nameBook, double price, int quantity, String img, Author author, Catagory catagory, Set<CodeBook> codeBooks) {
        this.nameBook = nameBook;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.author = author;
        this.catagory = catagory;
        this.codeBooks = codeBooks;
    }

    public int getId() {
        return id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public Set<CodeBook> getCodeBooks() {
        return codeBooks;
    }

    public void setCodeBooks(Set<CodeBook> codeBooks) {
        this.codeBooks = codeBooks;
    }

    public List<AccountMember> getAccountMembers() {
        return accountMembers;
    }

    public void setAccountMembers(List<AccountMember> accountMembers) {
        this.accountMembers = accountMembers;
    }
}
