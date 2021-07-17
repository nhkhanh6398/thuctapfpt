package vn.fpt.exception;

public class QuantityZeroException extends Exception {
    public String getMessage() {
        return "Book has quantity zero";
    }
}
