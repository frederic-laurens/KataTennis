package org.flaurens.tennis.domain.model;

public class SecondPlayerScoringEvent implements ScoringEvent {

    @Override
    public SetScore update(SetScore score) {
        return new SetScore(score.getFirstPlayerPoints(), score.getSecondPlayerPoints().getNextOnScoring());
    }
}
