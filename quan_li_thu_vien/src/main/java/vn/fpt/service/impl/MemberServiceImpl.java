package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.AccountMember;
import vn.fpt.model.Member;
import vn.fpt.repository.AccountMemberRepository;
import vn.fpt.repository.MemberRepository;
import vn.fpt.service.MemberService;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Override
    public List<Member> findAllAccount() {
        return memberRepository.findAll();
    }

    @Override
    public Member findAccountById(int id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public void createAccount(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void updateAccount(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void deleteAccount(int id) {
        memberRepository.deleteById(id);
    }
}
