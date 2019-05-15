package org.flaurens.tennis.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TennisGame {

    private GameScore gameScore;

    private final List<GameScore> scoreEvolution;

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
            GameScore nextScore = scoringEvent.update(gameScore);
            gameScore = nextScore;
            scoreEvolution.add(nextScore);
            if (nextScore.isFinalScore()) {
                this.isGameOver = true;
            }
            return isGameOver;
        }
        throw new GameIsAlreadyWonException("The final score is : "+ gameScore);
    }

    public List<GameScore> getScoreEvolution(){
       return this.scoreEvolution;
    }

    public GameScore getCurrentScore() {
        return gameScore;
    }
}
