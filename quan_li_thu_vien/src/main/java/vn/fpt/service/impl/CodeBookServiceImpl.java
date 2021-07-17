package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.Book;
import vn.fpt.model.CodeBook;
import vn.fpt.model.Status;
import vn.fpt.repository.BookRepository;
import vn.fpt.repository.CodeBookRepository;
import vn.fpt.service.CodeBookService;

import java.util.List;

@Service
public class CodeBookServiceImpl implements CodeBookService {
    @Autowired
    CodeBookRepository codeBookRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<CodeBook> findAll() {
        return codeBookRepository.findAll();
    }

    @Override
    public CodeBook findById(Integer id) {
        return codeBookRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CodeBook code) {
        codeBookRepository.save(code);
    }

    @Override
    public CodeBook findByBooks_IdAndCode(Integer id, Integer code) {
        return codeBookRepository.findByBooks_IdAndCode(id, code);
    }

    @Override

    public List<CodeBook> findAvailableCodeByBookId(Integer id) {
        return codeBookRepository.findAllByBooks_IdAndStatus_Id(id,1);

    }

    @Override
    public List<CodeBook> findUsedCodeByBookId(Integer id) {
        return codeBookRepository.findAllByBooks_IdAndStatus_Id(id, 2);

    }

    @Override
    public void returnBookByCode(Book book, Integer returnCode) {
        CodeBook code = codeBookRepository.findById(returnCode).orElse(null);
        book =code.getBooks();
        book.returnBook();
        bookRepository.save(book);
        code.setStatus(new Status(1,"available"));
    }
}
