package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.model.Catagory;

public interface CatagoryRepository extends JpaRepository<Catagory,Integer> {
}
