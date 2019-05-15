package org.flaurens.tennis.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisGameTest {

    @Test
    public void gameShouldStartWithNoPointsGivenToAnyPlayer(){
        TennisGame tennisGame = new TennisGame();
        Assertions.assertEquals(1, tennisGame.getScoreEvolution().size());
        GameScore expectedScore = new GameScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(0));
    }

    @Test
    public void firstPlayerShouldBeAbleToScorePoints() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        Assertions.assertEquals(2, tennisGame.getScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondScore = new GameScore(Points.SCORE15, Points.SCORE0);
        Assertions.assertEquals(expectedFirstScore, tennisGame.getScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondScore, tennisGame.getScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePoints() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        Assertions.assertEquals(2, tennisGame.getScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondScore = new GameScore(Points.SCORE0, Points.SCORE15);
        Assertions.assertEquals(expectedFirstScore, tennisGame.getScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondScore, tennisGame.getScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePointsTwice() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
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
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        boolean isGameOver = tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        Assertions.assertEquals(7, tennisGame.getScoreEvolution().size());
        GameScore expectedScore = new GameScore(Points.SCORE30, Points.WIN);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(6));
        Assertions.assertTrue(isGameOver);
    }

    @Test
    public void aGameShouldNotContinueAfterAPlayerHasWon() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        Assertions.assertThrows(GameIsAlreadyWonException.class, () -> tennisGame.updateGameScore(new FirstPlayerScoringEvent()));
    }

    @Test
    public void a40to40ScoreShouldLeadToActivationOfTheDeuceRule() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        GameScore expectedScore = new GameScore(Points.DEUCE, Points.DEUCE);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(6));
    }

    @Test
    public void followingADeuceThePlayerWhoWinsThePointShouldTakeTheAdvantage() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        GameScore expectedScore = new GameScore(Points.SCORE40, Points.ADV);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(7));
    }

    @Test void ifAPlayerLoosesTheAdvantageTheScoreShouldGetBackToDeuce() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        GameScore expectedScore = new GameScore(Points.DEUCE, Points.DEUCE);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(8));
    }

    @Test void thePlayerWhoHasTheAdvantageShouldWinIfHeScoresAgain() throws GameFlowException {
        TennisGame tennisGame = new TennisGame();
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new FirstPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        tennisGame.updateGameScore(new SecondPlayerScoringEvent());
        GameScore expectedScore = new GameScore(Points.SCORE40, Points.WIN);
        Assertions.assertEquals(expectedScore, tennisGame.getScoreEvolution().get(8));
    }

}
