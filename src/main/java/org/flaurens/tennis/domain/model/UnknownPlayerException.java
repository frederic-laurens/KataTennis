package org.flaurens.tennis.domain.model;

public class UnknownPlayerException extends GameFlowException {

    private static final long serialVersionUID = 8917758456143293558L;

    public UnknownPlayerException(String message) {
        super(message);
    }

    public UnknownPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownPlayerException(Throwable cause) {
        super(cause);
    }

    public UnknownPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
