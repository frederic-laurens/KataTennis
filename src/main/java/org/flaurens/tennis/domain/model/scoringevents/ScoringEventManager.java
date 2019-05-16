package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.Score;

public interface ScoringEventManager {

     Score update(ScoringEvent event, Score score);
}
