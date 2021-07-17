package vn.fpt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import vn.fpt.exception.NotAvailableException;
import vn.fpt.exception.WrongCodeException;
import vn.fpt.model.Book;
import vn.fpt.model.CodeBook;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    void save(Book book);
    Book findBookById(int id);
    List<Book> findBookByNameCatagory(String  name);
    void borrow(Book book, Integer usedCode);
    CodeBook getNextAvailableCode(Book book) throws NotAvailableException;
    void returnBook(Book book, Integer returnCode) throws NotAvailableException, WrongCodeException;
    boolean checkNoUsedCode(Book book);
    void update(Book book);
    void remove(int id);
    List<Book> findAllByName(String name);
    List<Book> findByAuthorContaining( String name);
    List<Book> findAllByBookOrCatagoryOrAuthor(String name);
    Page<Book> findAllByNameBookContaining(String name, Pageable pageable);
}
