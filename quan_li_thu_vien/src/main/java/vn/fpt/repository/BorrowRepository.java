package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.model.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow,Integer> {
}
