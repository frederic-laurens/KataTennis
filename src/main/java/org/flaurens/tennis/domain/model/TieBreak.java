package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scores.TieBreakScore;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;
import org.flaurens.tennis.domain.model.scoringevents.TieBreakScoringEventManager;

import java.util.ArrayList;

public class TieBreak extends TennisPhase {

    public TieBreak(){
        isOver = false;
        this.scoreEvolution = new ArrayList<>();
        TieBreakScore startingScore = TieBreakScore.initialScore();
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }

    @Override
    public Score manageEvent(ScoringEvent scoringEvent) {
        TieBreakScoringEventManager tieBreakScoringEventManager = new TieBreakScoringEventManager(scoringEvent);
        return tieBreakScoringEventManager.update((TieBreakScore) gameScore);
    }

    @Override
    public boolean isFirstPlayerWinning() {
        return (Integer) gameScore.getFirstPlayerPoints() > (Integer) gameScore.getSecondPlayerPoints();
    }
}
