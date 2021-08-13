package vn.fpt.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class AccountMember  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String account;
    private String pass;
    private Date dateCreate;
    @OneToOne(mappedBy = "account")
    private Member member;
    @ManyToMany(mappedBy = "accountMembers")
    List<Book> books;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public AccountMember(int id, String account, String pass, Date dateCreate, Member member, List<Book> books, List<Role> roles) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.dateCreate = dateCreate;
        this.member = member;
        this.books = books;
        this.roles = roles;
    }

    public AccountMember(int id, String account, String pass, Member member) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.member = member;
    }

    public AccountMember(int id, String account, String pass, Date dateCreate, Member member, List<Book> books) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.dateCreate = dateCreate;
        this.member = member;
        this.books = books;
    }

    public AccountMember(String account, String pass) {
        this.account = account;
        this.pass = pass;
    }

    public AccountMember(String account, String pass, Date dateCreate, Member member) {
        this.account = account;
        this.pass = pass;
        this.dateCreate = dateCreate;
        this.member = member;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public AccountMember() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return account;

    }
}
