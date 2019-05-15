package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.Score;

public interface ScoringEvent {

    Score update(Score score);
}
