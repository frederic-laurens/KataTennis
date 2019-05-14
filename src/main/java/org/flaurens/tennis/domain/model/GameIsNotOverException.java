package org.flaurens.tennis.domain.model;

public class GameIsNotOverException extends Throwable {

    private static final long serialVersionUID = 745454651213678614L;


    public GameIsNotOverException() {
    }

    public GameIsNotOverException(String message) {
        super(message);
    }

    public GameIsNotOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameIsNotOverException(Throwable cause) {
        super(cause);
    }

    public GameIsNotOverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
