package org.flaurens.tennis.domain.model.scoringevents;

import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scores.Score;

public class GameScoringEventManager implements ScoringEventManager{

    public GameScoringEventManager(){

    }

    public Score update(ScoringEvent event, Score score) {
        if(event.isFirstPlayerWins()){
            return updateForFirstPlayerWin((GameScore) score);
        } else {
            return updateForSecondPlayerWin((GameScore) score);
        }
    }

    private GameScore updateForFirstPlayerWin(GameScore score){
        if(Points.SCORE40.equals(score.getFirstPlayerPoints().getNextOnWonPoint()) && Points.SCORE40.equals(score.getSecondPlayerPoints().getNextOnLostPoint())){
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else if(Points.DEUCE.equals(score.getSecondPlayerPoints().getNextOnLostPoint())) {
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else {
            return new GameScore(score.getFirstPlayerPoints().getNextOnWonPoint(), score.getSecondPlayerPoints().getNextOnLostPoint());
        }
    }

    private GameScore updateForSecondPlayerWin(GameScore score){
        if(Points.SCORE40.equals(score.getFirstPlayerPoints().getNextOnLostPoint()) && Points.SCORE40.equals(score.getSecondPlayerPoints().getNextOnWonPoint())){
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else if(Points.DEUCE.equals(score.getFirstPlayerPoints().getNextOnLostPoint())) {
            return new GameScore(Points.DEUCE, Points.DEUCE);
        } else{
            return new GameScore(score.getFirstPlayerPoints().getNextOnLostPoint(), score.getSecondPlayerPoints().getNextOnWonPoint());
        }
    }
}

