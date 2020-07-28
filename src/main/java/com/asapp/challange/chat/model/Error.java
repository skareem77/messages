package com.asapp.challange.chat.model;

public class Error {
    String message;
    int code;

    public Error() {}

    public Error(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public Error(String message) {
        this.message = message;
        this.code = 500;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
