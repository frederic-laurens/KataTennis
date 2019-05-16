package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;
import org.flaurens.tennis.domain.model.exceptions.GamePhaseIsAlreadyWonException;
import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisGameTest {

    private TennisGame tennisGame;

    @BeforeEach
    public void createTennisGame(){
        tennisGame = new TennisGame();
    }

    private void firstPlayerWinsXTimes(int timesWon) throws GameFlowException {
        for(int i=0; i<timesWon; i++){
            tennisGame.updateGameScore(ScoringEvent.firstPlayerWins());
        }
    }

    private void secondPlayerWinsXTimes(int timesWon) throws GameFlowException{
        for(int i=0; i<timesWon; i++){
            tennisGame.updateGameScore(ScoringEvent.secondPlayerWins());
        }
    }

    @Test
    public void gameShouldStartWithNoPointsGivenToAnyPlayer(){
        Assertions.assertEquals(1, tennisGame.getScoreEvolution().size());
        GameScore expectedScore = new GameScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(0));
    }

    @Test
    public void firstPlayerShouldBeAbleToScorePoints() throws GameFlowException {
        firstPlayerWinsXTimes(1);
        Assertions.assertEquals(2, tennisGame.getScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondScore = new GameScore(Points.SCORE15, Points.SCORE0);
        Assertions.assertEquals(expectedFirstScore, tennisGame.getScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondScore, tennisGame.getScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePoints() throws GameFlowException {
        secondPlayerWinsXTimes(1);
        Assertions.assertEquals(2, tennisGame.getScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondScore = new GameScore(Points.SCORE0, Points.SCORE15);
        Assertions.assertEquals(expectedFirstScore, tennisGame.getScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondScore, tennisGame.getScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePointsTwice() throws GameFlowException {
        secondPlayerWinsXTimes(2);
        Assertions.assertEquals(3, tennisGame.getScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondScore = new GameScore(Points.SCORE0, Points.SCORE15);
        GameScore expectedThirdScore = new GameScore(Points.SCORE0, Points.SCORE30);
        Assertions.assertEquals(expectedFirstScore, tennisGame.getScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondScore, tennisGame.getScoreEvolution().get(1));
        Assertions.assertEquals(expectedThirdScore, tennisGame.getScoreEvolution().get(2));
    }


    @Test
    void aGameShouldBeOverAsSoonAsAPlayerReachesTheWinScore() throws GameFlowException {
        firstPlayerWinsXTimes(2);
        secondPlayerWinsXTimes(3);
        boolean isGameOver = tennisGame.updateGameScore(new ScoringEvent(false));
        Assertions.assertEquals(7, tennisGame.getScoreEvolution().size());
        GameScore expectedScore = new GameScore(Points.SCORE30, Points.WIN);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(6));
        Assertions.assertTrue(isGameOver);
    }

    @Test
    public void aGameShouldNotContinueAfterAPlayerHasWon() throws GameFlowException {
        firstPlayerWinsXTimes(2);
        secondPlayerWinsXTimes(4);
        Assertions.assertThrows(GamePhaseIsAlreadyWonException.class, () -> tennisGame.updateGameScore(ScoringEvent.firstPlayerWins()));
    }

    @Test
    public void a40to40ScoreShouldLeadToActivationOfTheDeuceRule() throws GameFlowException {
        firstPlayerWinsXTimes(2);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(2);
        GameScore expectedScore = new GameScore(Points.DEUCE, Points.DEUCE);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(6));
    }

    @Test
    public void followingADeuceThePlayerWhoWinsThePointShouldTakeTheAdvantage() throws GameFlowException {
        firstPlayerWinsXTimes(2);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(3);
        GameScore expectedScore = new GameScore(Points.SCORE40, Points.ADV);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(7));
    }

    @Test void ifAPlayerLoosesTheAdvantageTheScoreShouldGetBackToDeuce() throws GameFlowException {
        firstPlayerWinsXTimes(2);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(3);
        firstPlayerWinsXTimes(1);
        GameScore expectedScore = new GameScore(Points.DEUCE, Points.DEUCE);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(8));
    }

    @Test void thePlayerWhoHasTheAdvantageShouldWinIfHeScoresAgain() throws GameFlowException {
        firstPlayerWinsXTimes(2);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(4);
        GameScore expectedScore = new GameScore(Points.SCORE40, Points.WIN);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(8));
    }

}
