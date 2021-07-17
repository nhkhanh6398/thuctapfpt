package vn.fpt.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Set;

@Entity
public class Member implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Không được để trống")
    private String nameMember;
    @NotBlank(message = "Không được để trống")
    private String email;
    @NotBlank(message = "Không được để trống")
    private String phone;
    @NotBlank(message = "Không được để trống")
    private String birthday;
    @NotBlank(message = "Không được để trống")
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountMember account;

    public Member() {
    }

    public Member(int id, @NotBlank(message = "Không được để trống") String nameMember, @NotBlank(message = "Không được để trống") String email, @NotBlank(message = "Không được để trống") String phone, @NotBlank(message = "Không được để trống") String birthday, @NotBlank(message = "Không được để trống") String address, AccountMember account) {
        this.id = id;
        this.nameMember = nameMember;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.account = account;
    }

    public Member(@NotBlank(message = "Không được để trống") String nameMember, @NotBlank(message = "Không được để trống") String email, @NotBlank(message = "Không được để trống") String phone, @NotBlank(message = "Không được để trống") String birthday, @NotBlank(message = "Không được để trống") String address, AccountMember account) {
        this.nameMember = nameMember;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccountMember getAccount() {
        return account;
    }

    public void setAccount(AccountMember account) {
        this.account = account;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;
        if ("admin".equals(member.account.getAccount())){
            errors.rejectValue("account.account", "account.admin");
        }
    }
}
