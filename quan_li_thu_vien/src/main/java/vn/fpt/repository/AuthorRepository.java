package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
