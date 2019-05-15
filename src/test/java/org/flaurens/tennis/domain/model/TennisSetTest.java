package org.flaurens.tennis.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisSetTest {

    private Player firstPlayer = null;

    private Player secondPlayer = null;

    private TennisSet game = null;


    @BeforeEach
    public void createGameAndStartIt(){
        firstPlayer = new Player("A");
        secondPlayer = new Player("B");
        game = new TennisSet(firstPlayer, secondPlayer);
        game.startSet();
    }

    @Test
    public void gameShouldStartWithNoPointsGivenToAnyPlayer(){
        Assertions.assertEquals(1, game.getCurrentSetScoreEvolution().size());
        GameScore expectedEvent = new GameScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedEvent, game.getCurrentSetScoreEvolution().get(0));
    }

    @Test
    public void firstPlayerShouldBeAbleToScorePoints() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        Assertions.assertEquals(2, game.getCurrentSetScoreEvolution().size());
        GameScore expectedFirstEvent = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondEvent = new GameScore(Points.SCORE15, Points.SCORE0);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondEvent,game.getCurrentSetScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePoints() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(secondPlayer);
        Assertions.assertEquals(2, game.getCurrentSetScoreEvolution().size());
        GameScore expectedFirstEvent = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondEvent = new GameScore(Points.SCORE0, Points.SCORE15);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondEvent,game.getCurrentSetScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePointsTwice() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        Assertions.assertEquals(3, game.getCurrentSetScoreEvolution().size());
        GameScore expectedFirstEvent = new GameScore(Points.SCORE0, Points.SCORE0);
        GameScore expectedSecondEvent = new GameScore(Points.SCORE0, Points.SCORE15);
        GameScore expectedThirdEvent = new GameScore(Points.SCORE0, Points.SCORE30);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondEvent,game.getCurrentSetScoreEvolution().get(1));
        Assertions.assertEquals(expectedThirdEvent,game.getCurrentSetScoreEvolution().get(2));
    }


    @Test
    void gameShouldHandleUnknownPlayersByNotUpdatingTheScore(){
        Assertions.assertThrows(UnknownPlayerException.class, () -> game.grantPoint(new Player("Unknown")));
        Assertions.assertEquals(1, game.getCurrentSetScoreEvolution().size());
        GameScore expectedFirstEvent = new GameScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
    }

    @Test
    void aSetShouldBeOverAsSoonAsAPlayerReachesTheWinScore() throws UnknownPlayerException, GameIsAlreadyWonException, GameIsNotOverException{
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        Assertions.assertEquals(7, game.getCurrentSetScoreEvolution().size());
        GameScore expectedScore = new GameScore(Points.SCORE30, Points.WIN);
        Assertions.assertEquals(expectedScore,game.getCurrentSetScoreEvolution().get(6));
        Assertions.assertTrue(game.isSetOver());
        Player winner = game.finishSetAndDeclareWinner();
        Assertions.assertEquals(secondPlayer, winner);
    }

    @Test
    public void aSetShouldNotContinueAfterAPlayerHasWon() throws UnknownPlayerException,GameIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        Assertions.assertThrows(GameIsAlreadyWonException.class, () -> game.grantPoint(firstPlayer));
    }

    @Test
    public void aWinnerShouldNotBeDeclaredBeforeTheGameIsOver() throws UnknownPlayerException, GameIsAlreadyWonException, GameIsNotOverException{
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        Assertions.assertThrows(GameIsNotOverException.class, () -> game.finishSetAndDeclareWinner());
    }

    @Test
    public void a40to40ScoreShouldLeadToActivationOfTheDeuceRule() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        GameScore expectedScore = new GameScore(Points.DEUCE, Points.DEUCE);
        Assertions.assertEquals(expectedScore,game.getCurrentSetScoreEvolution().get(6));
    }

    @Test
    public void followingADeuceThePlayerWhoWinsThePointShouldTakeTheAdvantage() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        GameScore expectedScore = new GameScore(Points.SCORE40, Points.ADV);
        Assertions.assertEquals(expectedScore,game.getCurrentSetScoreEvolution().get(7));
    }

    @Test void ifAPlayerLoosesTheAdvantageTheScoreShouldGetBackToDeuce() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        GameScore expectedScore = new GameScore(Points.DEUCE, Points.DEUCE);
        Assertions.assertEquals(expectedScore,game.getCurrentSetScoreEvolution().get(8));
    }

    @Test void ifThePlayerWhoHasTheAdvantageShouldWinIfHeScoresAgain() throws UnknownPlayerException, GameIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        GameScore expectedScore = new GameScore(Points.SCORE40, Points.WIN);
        Assertions.assertEquals(expectedScore,game.getCurrentSetScoreEvolution().get(8));
    }

}
