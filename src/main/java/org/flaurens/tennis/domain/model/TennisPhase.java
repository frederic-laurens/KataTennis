package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameIsAlreadyWonException;
import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class TennisPhase {

    protected Score gameScore;

    protected List<Score> scoreEvolution;

    protected boolean isOver;

    public boolean updateGameScore(ScoringEvent scoringEvent) throws GameIsAlreadyWonException {
        if(!isOver) {
            Score nextScore = scoringEvent.update(gameScore);
            gameScore = nextScore;
            scoreEvolution.add(nextScore);
            if (nextScore.isWinningScore()) {
                this.isOver = true;
            }
            return isOver;
        }
        throw new GameIsAlreadyWonException("The final score is : "+ gameScore);
    }

    public List<Score> getScoreEvolution(){
        return new ArrayList<>(this.scoreEvolution);
    }

    public Score getCurrentScore() {
        return this.gameScore.getCopy();
    }
}
