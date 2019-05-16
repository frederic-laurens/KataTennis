package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scoringevents.GameScoringEventManager;


import java.util.ArrayList;

public class TennisGame extends TennisPhase {

    public TennisGame(){
        isOver = false;
        this.scoringEventManager = new GameScoringEventManager();
        this.scoreEvolution = new ArrayList<>();
        GameScore startingScore = GameScore.initialScore();
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }

    @Override
    public boolean isFirstPlayerWinning() {
        return Points.WIN.equals(gameScore.getFirstPlayerPoints());
    }
}
