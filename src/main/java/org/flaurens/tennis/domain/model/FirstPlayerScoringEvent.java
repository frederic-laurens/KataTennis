package org.flaurens.tennis.domain.model;

public class FirstPlayerScoringEvent implements ScoringEvent {

    @Override
    public GameScore update(GameScore score) {
        if(Points.SCORE40.equals(score.getFirstPlayerPoints().getNextOnWonPoint()) && Points.SCORE40.equals(score.getSecondPlayerPoints().getNextOnLostPoint())){
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else if(Points.DEUCE.equals(score.getSecondPlayerPoints().getNextOnLostPoint())) {
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else {
            return new GameScore(score.getFirstPlayerPoints().getNextOnWonPoint(), score.getSecondPlayerPoints().getNextOnLostPoint());
        }
    }
}