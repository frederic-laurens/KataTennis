package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scores.TieBreakScore;


public abstract class TieBreakScoringEvent implements ScoringEvent {

    public Score update(Score score){
        return this.updateTieBreakScore((TieBreakScore) score);
    }

    protected abstract TieBreakScore updateTieBreakScore(TieBreakScore tieBreakScore);
}
