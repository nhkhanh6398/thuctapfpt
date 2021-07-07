package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.Borrow;
import vn.fpt.model.Catagory;
import vn.fpt.repository.BookRepository;
import vn.fpt.repository.BorrowRepository;
import vn.fpt.service.BorrowService;

import java.util.List;
@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowRepository borrowRepository;
    @Override
    public List<Borrow> findAllBorrow() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow findBorrowById(int id) {
        return borrowRepository.findById(id).orElse(null);
    }

    @Override
    public void createBorrow(Borrow borrow) {
        borrowRepository.save(borrow);
    }

    @Override
    public void updateBorrow(Borrow borrow) {
        borrowRepository.save(borrow);
    }

    @Override
    public void deleteBorrow(Borrow borrow) {
        borrowRepository.delete(borrow);
    }
}
