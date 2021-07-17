package vn.fpt.model;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "status")
    private Set<CodeBook> codeAvailableSet;

    public Status(Integer id, String status, Set<CodeBook> codeAvailableSet) {
        this.id = id;
        this.status = status;
        this.codeAvailableSet = codeAvailableSet;
    }

    public Status() {
    }

    public Status(String status, Set<CodeBook> codeAvailableSet) {
        this.status = status;
        this.codeAvailableSet = codeAvailableSet;
    }

    public Status(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Status(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<CodeBook> getCodeAvailableSet() {
        return codeAvailableSet;
    }

    public void setCodeAvailableSet(Set<CodeBook> codeAvailableSet) {
        this.codeAvailableSet = codeAvailableSet;
    }
}
