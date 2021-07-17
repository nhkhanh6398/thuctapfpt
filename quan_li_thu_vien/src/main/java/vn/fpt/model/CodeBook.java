package vn.fpt.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
@Entity
public class CodeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Range(min = 10000, max = 99999)
    private Integer code;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book books;
    @ManyToOne()
    @JoinColumn(name="status_id")
    private Status status;

    public CodeBook() {
    }

    public CodeBook(Integer id, @Range(min = 10000, max = 99999) Integer code) {
        this.id = id;
        this.code = code;
    }

    public CodeBook(int code, Book book, Status status) {
        this.code = code;
        this.books = book;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public CodeBook(@Range(min = 10000, max = 99999) Integer code, Book books) {
        this.code = code;
        this.books = books;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
