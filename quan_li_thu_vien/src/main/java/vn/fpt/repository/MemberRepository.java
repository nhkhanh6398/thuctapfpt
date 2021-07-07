package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.model.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
