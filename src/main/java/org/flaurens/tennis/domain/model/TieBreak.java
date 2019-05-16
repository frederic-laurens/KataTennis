package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.scores.TieBreakScore;
import org.flaurens.tennis.domain.model.scoringevents.TieBreakScoringEventManager;

import java.util.ArrayList;

public class TieBreak extends TennisPhase {

    public TieBreak(){
        isOver = false;
        this.scoringEventManager = new TieBreakScoringEventManager();
        this.scoreEvolution = new ArrayList<>();
        TieBreakScore startingScore = TieBreakScore.initialScore();
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }

    @Override
    public boolean isFirstPlayerWinning() {
        return (Integer) gameScore.getFirstPlayerPoints() > (Integer) gameScore.getSecondPlayerPoints();
    }
}
