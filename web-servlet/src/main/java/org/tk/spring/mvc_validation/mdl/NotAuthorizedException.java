package org.tk.spring.mvc_validation.mdl;

public class NotAuthorizedException extends Exception {

    public NotAuthorizedException(String message, Throwable t) {
        super(message, t);
    }
}
