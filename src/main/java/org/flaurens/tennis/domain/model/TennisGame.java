package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scoringevents.GameScoringEventManager;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;

import java.util.ArrayList;


public class TennisGame extends TennisPhase {

    public TennisGame(){
        isOver = false;
        this.scoreEvolution = new ArrayList<>();
        GameScore startingScore = GameScore.initialScore();
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }

    @Override
    public Score manageEvent(ScoringEvent scoringEvent) {
        GameScoringEventManager gameScoringEventManager = new GameScoringEventManager(scoringEvent);
        return gameScoringEventManager.update((GameScore) gameScore);
    }


    @Override
    public boolean isFirstPlayerWinning() {
        return Points.WIN.equals(gameScore.getFirstPlayerPoints());
    }
}
