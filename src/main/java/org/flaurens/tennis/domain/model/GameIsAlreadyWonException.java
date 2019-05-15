package org.flaurens.tennis.domain.model;

public class GameIsAlreadyWonException extends GameFlowException {

    private static final long serialVersionUID = 7452758456569893444L;

    public GameIsAlreadyWonException(String message) {
        super(message);
    }

    public GameIsAlreadyWonException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameIsAlreadyWonException(Throwable cause) {
        super(cause);
    }

    public GameIsAlreadyWonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
