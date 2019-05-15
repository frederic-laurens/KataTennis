package org.flaurens.tennis.domain.model;

public class GameFlowException extends Throwable {

    private static final long serialVersionUID = 5410364132122378614L;

    public GameFlowException(String message) {
        super(message);
    }

    public GameFlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameFlowException(Throwable cause) {
        super(cause);
    }

    public GameFlowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
