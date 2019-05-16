package org.flaurens.tennis.domain.model.exceptions;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;

public class MatchIsNotOverException extends GameFlowException {

    private static final long serialVersionUID = 745454651213678614L;

    public MatchIsNotOverException(String message) {
        super(message);
    }

    public MatchIsNotOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchIsNotOverException(Throwable cause) {
        super(cause);
    }

    public MatchIsNotOverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
