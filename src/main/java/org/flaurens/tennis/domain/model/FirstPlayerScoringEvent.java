package org.flaurens.tennis.domain.model;

public class FirstPlayerScoringEvent implements ScoringEvent {

    @Override
    public SetScore update(SetScore score) {
        return new SetScore(score.getFirstPlayerPoints().getNextOnScoring(), score.getSecondPlayerPoints());
    }
}