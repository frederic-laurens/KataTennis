package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scores.TieBreakScore;


public class TieBreakScoringEventManager implements ScoringEventManager {

    public TieBreakScoringEventManager(){
    }

    public TieBreakScore update(ScoringEvent event, Score score) {
        if(event.isFirstPlayerWins()){
            return updateForFirstPlayerWin((TieBreakScore) score);
        } else {
            return updateForSecondPlayerWin((TieBreakScore) score);
        }
    }

    private TieBreakScore updateForFirstPlayerWin(TieBreakScore score) {
        return new TieBreakScore(score.getFirstPlayerPoints()+1,score.getSecondPlayerPoints());
    }

    private TieBreakScore updateForSecondPlayerWin(TieBreakScore score) {
        return new TieBreakScore(score.getFirstPlayerPoints(),score.getSecondPlayerPoints()+1);
    }
}
