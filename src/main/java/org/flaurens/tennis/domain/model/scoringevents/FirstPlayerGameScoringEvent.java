package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scores.Score;

public class FirstPlayerGameScoringEvent extends GameScoringEvent {

    @Override
    public GameScore updateGameScore(GameScore score) {

        if(Points.SCORE40.equals(score.getFirstPlayerPoints().getNextOnWonPoint()) && Points.SCORE40.equals(score.getSecondPlayerPoints().getNextOnLostPoint())){
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else if(Points.DEUCE.equals(score.getSecondPlayerPoints().getNextOnLostPoint())) {
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else {
            return new GameScore(score.getFirstPlayerPoints().getNextOnWonPoint(), score.getSecondPlayerPoints().getNextOnLostPoint());
        }
    }
}