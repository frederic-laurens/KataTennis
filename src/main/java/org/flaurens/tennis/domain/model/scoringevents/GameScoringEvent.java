package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scores.GameScore;

public abstract class GameScoringEvent implements ScoringEvent{

    public Score update(Score score){
        return this.updateGameScore((GameScore) score);
    }

    protected abstract GameScore updateGameScore(GameScore tieBreakScore);
}

