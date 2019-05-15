package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;

public class SecondPlayerGameScoringEvent extends GameScoringEvent {

    @Override
    public GameScore updateGameScore(GameScore score) {
        if(Points.SCORE40.equals(score.getFirstPlayerPoints().getNextOnLostPoint()) && Points.SCORE40.equals(score.getSecondPlayerPoints().getNextOnWonPoint())){
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else if(Points.DEUCE.equals(score.getFirstPlayerPoints().getNextOnLostPoint())) {
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else{
            return new GameScore(score.getFirstPlayerPoints().getNextOnLostPoint(), score.getSecondPlayerPoints().getNextOnWonPoint());
        }
    }
}
