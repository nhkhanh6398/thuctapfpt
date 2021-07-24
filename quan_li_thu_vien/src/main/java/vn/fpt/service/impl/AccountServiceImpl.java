package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.AccountMember;
import vn.fpt.model.Book;
import vn.fpt.repository.AccountMemberRepository;
import vn.fpt.service.AccountService;

import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMemberRepository accountMemberRepository;
    @Override
    public AccountMember checkLogin(String user, String pass) {
        return accountMemberRepository.checkLogin(user,pass);
    }

//    @Override
//    public Set<Book> findAllByBooks() {
//        return accountMemberRepository.findAllByBooks();
//    }



}
