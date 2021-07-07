package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fpt.model.Book;
import vn.fpt.repository.BookRepository;
import vn.fpt.service.BookService;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findBookByNameCatagory(String name) {
        return bookRepository.findAllByIdCatagory(name);
    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }



    @Override
    public void remove(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByName(String name) {
        return bookRepository.findByNameBookContaining(name);
    }

    @Override
    public Page<Book> findAllByNameBookContaining(String name, Pageable pageable) {
        return bookRepository.findAllByNameBookContaining(name, pageable);
    }


}
