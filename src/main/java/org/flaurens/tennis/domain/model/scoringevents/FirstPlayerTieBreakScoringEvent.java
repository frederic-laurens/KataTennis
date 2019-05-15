package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.TieBreakScore;

public class FirstPlayerTieBreakScoringEvent extends TieBreakScoringEvent {

    @Override
    public TieBreakScore updateTieBreakScore(TieBreakScore score) {
        return new TieBreakScore(score.getFirstPlayerPoints()+1,score.getSecondPlayerPoints());
    }
}
