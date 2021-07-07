package vn.fpt.service;

import vn.fpt.model.Borrow;
import vn.fpt.model.Catagory;

import java.util.List;

public interface BorrowService {
    public List<Borrow> findAllBorrow();

    public Borrow findBorrowById(int id);

    public void createBorrow(Borrow borrow);

    public void updateBorrow(Borrow borrow);

    public void deleteBorrow(Borrow borrow);
}

