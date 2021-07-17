package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.model.Status;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}
