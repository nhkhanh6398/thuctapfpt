package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fpt.exception.NotAvailableException;
import vn.fpt.exception.WrongCodeException;
import vn.fpt.model.Book;
import vn.fpt.model.CodeBook;
import vn.fpt.model.Status;
import vn.fpt.repository.BookRepository;
import vn.fpt.service.BookService;
import vn.fpt.service.CodeBookService;

import java.util.List;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CodeBookService codeBookService;
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
        Status available = new Status(1);
        for (int i = 0; i < book.getQuantity(); i++) {
            int n = 10000 + new Random().nextInt(90000);
            CodeBook code = new CodeBook(n, bookRepository.findById(book.getId()).orElse(null), available);
            book.generateCode(code);
            codeBookService.save(code);
        }
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
    public void borrow(Book book, Integer usedCode) {
//        List<CodeBook> codeList = codeBookService.findAllCodeByBookId(book.getId());
//        for (CodeBook code : codeList) {
//            if (code.getCode().equals(usedCode)) {
//                code.setStatus(new Status(2, "used"));
//                break;
//            }
//        }
        CodeBook codeBook = codeBookService.findByBooks_IdAndCode(book.getId(), usedCode);
        Book book1 = bookRepository.findById(book.getId()).orElse(null);
        if (codeBook != null){
            codeBook.setStatus(new Status(2));
            codeBookService.save(codeBook);
        }
        if (book1 != null){
            if (book1.getQuantity()>0) {
                book1.setQuantity(book1.getQuantity() - 1);
            }
            bookRepository.save(book1);
        }
//        book.borrow();
//        bookRepository.save(book);
    }


    @Override
    public CodeBook getNextAvailableCode(Book book) throws NotAvailableException {

        List<CodeBook> codeList = codeBookService.findAvailableCodeByBookId(book.getId());
        if (codeList.size() == 0) {
            throw new NotAvailableException();
        }
        return codeList.get(0);
    }

    @Override
    public void returnBook(Book book, Integer returnCode) throws NotAvailableException, WrongCodeException {

        CodeBook codeBook = codeBookService.findByBooks_IdAndCode(book.getId(), returnCode);

        if (codeBook != null){
            codeBook.setStatus(new Status(1));
            codeBookService.save(codeBook);
            Book book1 = bookRepository.findById(book.getId()).orElse(null);
            if (book1 != null){
                book1.returnBook();
                bookRepository.save(book1);
            }
        } else {
            throw new NotAvailableException();
        }

//        List<CodeBook> codeList = codeBookService.findUsedCodeByBookId(book.getId());
//        if (codeList.size() == 0) {
//            throw new NotAvailableException();
//        }
//        boolean isCorrectCode = false;
//        for (CodeBook code : codeList) {
//            if (code.getCode().equals(returnCode)) {
//                code.setStatus(new Status(1, "available"));
//                codeBookService.save(code);
//                book.returnBook();
//                bookRepository.save(book);
//                isCorrectCode = true;
//                break;
//            }
//        }
//        if (!isCorrectCode) {
//            throw new WrongCodeException();
//        }
    }

    @Override
    public boolean checkNoUsedCode(Book book) {
//        List<CodeBook> availableCodeList = codeBookService.findAvailableCodeByBookId(book.getId());
//        List<CodeBook> allCodeList = codeBookService.findAllCodeByBookId(book.getId());
//        return availableCodeList.size() == allCodeList.size();
        return false;
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
    public List<Book> findByAuthorContaining(String name) {
        return bookRepository.findByAuthorNameContaining(name);
    }

    @Override
    public List<Book> findAllByBookOrCatagoryOrAuthor(String name) {
        return bookRepository.searchByAll(name);
    }

    @Override
    public Page<Book> findAllByNameBookContaining(String name, Pageable pageable) {
        return bookRepository.findAllByNameBookContaining(name, pageable);
    }


}
