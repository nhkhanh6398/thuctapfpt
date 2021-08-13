package vn.fpt.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roleName;

    @ManyToMany
    private Set<AccountMember> accountMemberSet;

    public Role() {
    }

    public Role(Integer id, String roleName, Set<AccountMember> accountMemberSet) {
        this.id = id;
        this.roleName = roleName;
        this.accountMemberSet = accountMemberSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<AccountMember> getAccountMemberSet() {
        return accountMemberSet;
    }

    public void setAccountMemberSet(Set<AccountMember> accountMemberSet) {
        this.accountMemberSet = accountMemberSet;
    }
}
