package org.flaurens.tennis.domain.model;

public class SetIsAlreadyWonException extends Throwable {

    private static final long serialVersionUID = 7452758456569893444L;

    public SetIsAlreadyWonException(String message) {
        super(message);
    }

    public SetIsAlreadyWonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetIsAlreadyWonException(Throwable cause) {
        super(cause);
    }

    public SetIsAlreadyWonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
