package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.AccountMember;
import vn.fpt.repository.AccountMemberRepository;
import vn.fpt.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMemberRepository accountMemberRepository;
    @Override
    public AccountMember checkLogin(String user, String pass) {
        return accountMemberRepository.checkLogin(user,pass);
    }
}
