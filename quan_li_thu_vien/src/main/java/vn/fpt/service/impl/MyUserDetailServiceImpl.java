package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.fpt.model.AccountMember;
import vn.fpt.model.MyUserDetail;
import vn.fpt.repository.AccountMemberRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountMemberRepository accountMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountMember accountMember = accountMemberRepository.findByAccount(username);
        if (accountMember==null){
            throw new UsernameNotFoundException("Not Found");
        }
        return new MyUserDetail(accountMember);
    }
}
