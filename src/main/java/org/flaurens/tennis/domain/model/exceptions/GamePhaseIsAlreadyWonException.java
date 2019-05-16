package org.flaurens.tennis.domain.model.exceptions;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;

public class GamePhaseIsAlreadyWonException extends GameFlowException {

    private static final long serialVersionUID = 7452758456569893444L;

    public GamePhaseIsAlreadyWonException(String message) {
        super(message);
    }

    public GamePhaseIsAlreadyWonException(String message, Throwable cause) {
        super(message, cause);
    }

    public GamePhaseIsAlreadyWonException(Throwable cause) {
        super(cause);
    }

    public GamePhaseIsAlreadyWonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
