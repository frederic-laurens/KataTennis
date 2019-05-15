package org.flaurens.tennis.domain.model.exceptions;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;

public class SetIsNotOverException extends GameFlowException {

    private static final long serialVersionUID = 745454651213678614L;

    public SetIsNotOverException(String message) {
        super(message);
    }

    public SetIsNotOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetIsNotOverException(Throwable cause) {
        super(cause);
    }

    public SetIsNotOverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
