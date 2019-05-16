package org.flaurens.tennis.domain.model.scores;

public interface Score {

    Object getFirstPlayerPoints();

    Object getSecondPlayerPoints();

    boolean isWinningScore();

    Score getCopy();

}

