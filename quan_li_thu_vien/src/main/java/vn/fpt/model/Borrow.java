package vn.fpt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date startDay;
    private Date endDay;
    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    private Member member;
    @ManyToMany(mappedBy = "borrow")
    Set<Book> books;

    public Borrow() {
    }

    public Borrow(Date startDay, Date endDay, Member member, Set<Book> books) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.member = member;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
