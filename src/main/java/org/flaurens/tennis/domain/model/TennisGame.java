package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameIsAlreadyWonException;
import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scores.Score;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;

import java.util.ArrayList;
import java.util.List;

public class TennisGame {

    private Score gameScore;

    private final List<Score> scoreEvolution;

    private boolean isGameOver;

    public TennisGame(){
        isGameOver = false;
        this.scoreEvolution = new ArrayList<>();
        GameScore startingScore = new GameScore(Points.SCORE0, Points.SCORE0);
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }

    public boolean updateGameScore(ScoringEvent scoringEvent) throws GameIsAlreadyWonException {
        if(!isGameOver) {
            Score nextScore = scoringEvent.update(gameScore);
            gameScore = nextScore;
            scoreEvolution.add(nextScore);
            if (nextScore.isWinningScore()) {
                this.isGameOver = true;
            }
            return isGameOver;
        }
        throw new GameIsAlreadyWonException("The final score is : "+ gameScore);
    }

    public List<Score> getScoreEvolution(){
       return new ArrayList<>(this.scoreEvolution);
    }

    public Score getCurrentScore() {
        return this.gameScore.getCopy();
    }
}
