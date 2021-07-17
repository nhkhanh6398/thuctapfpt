package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.model.CodeBook;

import java.util.List;

public interface CodeBookRepository extends JpaRepository<CodeBook,Integer> {

    List<CodeBook> findAllByBooks(Integer id);

    CodeBook findByBooks_IdAndCode(Integer id, Integer code);

    List<CodeBook> findAllByBooks_IdAndStatus_Id(Integer id, Integer idStatus);


//    List<CodeBook> findCodeByBookAndAndStatus(Integer id, Integer statusID);
}
