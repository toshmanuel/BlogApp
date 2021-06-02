package com.blogapp.blogapp.web.exceptions;

public class NullPostException extends Throwable {
    public NullPostException() {
        this("");
    }

    public NullPostException(String message) {
        this(message, null);
    }

    public NullPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
