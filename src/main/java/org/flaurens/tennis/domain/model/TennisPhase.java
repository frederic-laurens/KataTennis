package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GamePhaseIsAlreadyWonException;
import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEventManager;

import java.util.ArrayList;
import java.util.List;

public abstract class TennisPhase {

    protected Score gameScore;

    protected List<Score> scoreEvolution;

    protected boolean isOver;

    protected ScoringEventManager scoringEventManager;

    public boolean updateGameScore(ScoringEvent scoringEvent) throws GamePhaseIsAlreadyWonException {
        if(!isOver) {
            gameScore = scoringEventManager.update(scoringEvent,gameScore);
            scoreEvolution.add(gameScore);
            if (gameScore.isWinningScore()) {
                this.isOver = true;
            }
            return isOver;
        }
        throw new GamePhaseIsAlreadyWonException("The final score is : "+ gameScore);
    }

    public List<Score> getScoreEvolution(){
        return new ArrayList<>(this.scoreEvolution);
    }

    public Score getCurrentScore() {
        return this.gameScore.getCopy();
    }

    public abstract boolean isFirstPlayerWinning();
}
