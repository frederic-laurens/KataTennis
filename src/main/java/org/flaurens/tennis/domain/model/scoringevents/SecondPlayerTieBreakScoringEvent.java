package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.TieBreakScore;

public class SecondPlayerTieBreakScoringEvent extends TieBreakScoringEvent {

    @Override
    public TieBreakScore updateTieBreakScore(TieBreakScore score) {
        return new TieBreakScore(score.getFirstPlayerPoints(),score.getSecondPlayerPoints()+1);
    }
}
