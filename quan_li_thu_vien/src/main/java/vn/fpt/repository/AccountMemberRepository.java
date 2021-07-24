package vn.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.fpt.model.AccountMember;
import vn.fpt.model.Book;

import java.util.List;
import java.util.Set;

public interface AccountMemberRepository extends JpaRepository<AccountMember,Integer> {

    @Query("select a from AccountMember a where a.account =:account and a.pass=:pass")
    AccountMember checkLogin(@Param("account") String user,@Param("pass") String pass);

//    Set<Book> findAllByBooks();

}
