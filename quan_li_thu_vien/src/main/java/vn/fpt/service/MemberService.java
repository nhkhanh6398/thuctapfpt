package vn.fpt.service;


import vn.fpt.model.Member;

import java.util.List;

public interface MemberService {
    public List<Member> findAllAccount();

    public Member findAccountById(int id);

    public void createAccount(Member member);

    public void updateAccount(Member member);

    public void deleteAccount(int id);
}
