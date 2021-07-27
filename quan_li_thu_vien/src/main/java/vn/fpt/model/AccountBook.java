package vn.fpt.model;

public class AccountBook {
    private String nameBook;
    private String nameAccount;

    public AccountBook() {
    }

    public AccountBook(String nameBook, String nameAccount) {
        this.nameBook = nameBook;
        this.nameAccount = nameAccount;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }
}
