package vn.fpt.service;

import vn.fpt.model.AccountMember;

public interface AccountService {
    AccountMember checkLogin(String user, String pass);
}
