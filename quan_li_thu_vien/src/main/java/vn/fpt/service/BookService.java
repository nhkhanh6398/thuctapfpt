package vn.fpt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fpt.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    void save(Book book);
    public Book findBookById(int id);
    List<Book> findBookByNameCatagory(String  name);
    void update(Book book);
    void remove(int id);
    List<Book> findAllByName(String name);
    Page<Book> findAllByNameBookContaining(String name, Pageable pageable);
}
