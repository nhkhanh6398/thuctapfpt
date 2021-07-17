package vn.fpt.exception;

public class WrongCodeException extends Exception{
    public String getMessage() {
        return "Your code is invalid";
    }
}
