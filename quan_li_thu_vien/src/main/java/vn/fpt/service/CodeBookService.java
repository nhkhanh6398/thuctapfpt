package vn.fpt.service;

import vn.fpt.model.Book;
import vn.fpt.model.CodeBook;

import java.util.List;

public interface CodeBookService {
    List<CodeBook> findAll();
    CodeBook findById(Integer id);

    void save(CodeBook code);

    CodeBook findByBooks_IdAndCode(Integer id, Integer code);

    List<CodeBook> findAvailableCodeByBookId(Integer id);

    List<CodeBook> findUsedCodeByBookId(Integer id);

    void returnBookByCode(Book book, Integer returnCode);
}
