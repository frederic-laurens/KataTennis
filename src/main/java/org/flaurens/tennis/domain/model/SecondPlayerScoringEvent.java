package org.flaurens.tennis.domain.model;

public class SecondPlayerScoringEvent implements ScoringEvent {

    @Override
    public GameScore update(GameScore score) {
        if(Points.SCORE40.equals(score.getFirstPlayerPoints().getNextOnLostPoint()) && Points.SCORE40.equals(score.getSecondPlayerPoints().getNextOnWonPoint())){
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else if(Points.DEUCE.equals(score.getFirstPlayerPoints().getNextOnLostPoint())) {
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else{
            return new GameScore(score.getFirstPlayerPoints().getNextOnLostPoint(), score.getSecondPlayerPoints().getNextOnWonPoint());
        }
    }
}
