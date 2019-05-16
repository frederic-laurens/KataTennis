package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scores.TieBreakScore;


public class TieBreakScoringEventManager {

    private final ScoringEvent event;

    public TieBreakScoringEventManager(ScoringEvent event){
        this.event = event;
    }

    public TieBreakScore update(TieBreakScore score) {
        if(event.isFirstPlayerWins()){
            return updateForFirstPlayerWin(score);
        } else {
            return updateForSecondPlayerWin(score);
        }
    }

    private TieBreakScore updateForFirstPlayerWin(TieBreakScore score) {
        return new TieBreakScore(score.getFirstPlayerPoints()+1,score.getSecondPlayerPoints());
    }

    private TieBreakScore updateForSecondPlayerWin(TieBreakScore score) {
        return new TieBreakScore(score.getFirstPlayerPoints(),score.getSecondPlayerPoints()+1);
    }
}
