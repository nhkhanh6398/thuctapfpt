package vn.fpt.service;

import vn.fpt.model.AccountMember;
import vn.fpt.model.Book;

import java.util.List;
import java.util.Set;

public interface AccountService {
    AccountMember checkLogin(String user, String pass);
//    Set<Book> findAllByBooks();
    List<AccountMember> finAll();
}
