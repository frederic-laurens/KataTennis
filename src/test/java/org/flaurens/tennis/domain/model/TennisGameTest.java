package org.flaurens.tennis.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisGameTest {

    private Player firstPlayer = null;

    private Player secondPlayer = null;

    private TennisGame game = null;


    @BeforeEach
    public void createGameAndStartIt(){
        firstPlayer = new Player("A");
        secondPlayer = new Player("B");
        game = new TennisGame(firstPlayer, secondPlayer);
        game.startSet();
    }

    @Test
    public void gameShouldStartWithNoPointsGivenToAnyPlayer(){
        Assertions.assertEquals(1, game.getCurrentSetScoreEvolution().size());
        SetScore expectedEvent = new SetScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedEvent, game.getCurrentSetScoreEvolution().get(0));
    }

    @Test
    public void firstPlayerShouldBeAbleToScorePoints() throws UnknownPlayerException, SetIsAlreadyWonException{
        game.grantPoint(firstPlayer);
        Assertions.assertEquals(2, game.getCurrentSetScoreEvolution().size());
        SetScore expectedFirstEvent = new SetScore(Points.SCORE0, Points.SCORE0);
        SetScore expectedSecondEvent = new SetScore(Points.SCORE15, Points.SCORE0);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondEvent,game.getCurrentSetScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePoints() throws UnknownPlayerException, SetIsAlreadyWonException{
        game.grantPoint(secondPlayer);
        Assertions.assertEquals(2, game.getCurrentSetScoreEvolution().size());
        SetScore expectedFirstEvent = new SetScore(Points.SCORE0, Points.SCORE0);
        SetScore expectedSecondEvent = new SetScore(Points.SCORE0, Points.SCORE15);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondEvent,game.getCurrentSetScoreEvolution().get(1));
    }

    @Test
    public void secondPlayerShouldBeAbleToScorePointsTwice() throws UnknownPlayerException, SetIsAlreadyWonException{
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        Assertions.assertEquals(3, game.getCurrentSetScoreEvolution().size());
        SetScore expectedFirstEvent = new SetScore(Points.SCORE0, Points.SCORE0);
        SetScore expectedSecondEvent = new SetScore(Points.SCORE0, Points.SCORE15);
        SetScore expectedThirdEvent = new SetScore(Points.SCORE0, Points.SCORE30);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
        Assertions.assertEquals(expectedSecondEvent,game.getCurrentSetScoreEvolution().get(1));
        Assertions.assertEquals(expectedThirdEvent,game.getCurrentSetScoreEvolution().get(2));
    }


    @Test
    void gameShouldHandleUnknownPlayersByNotUpdatingTheScore(){
        Assertions.assertThrows(UnknownPlayerException.class, () -> {
            game.grantPoint(new Player("Unknown"));
        });
        Assertions.assertEquals(1, game.getCurrentSetScoreEvolution().size());
        SetScore expectedFirstEvent = new SetScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedFirstEvent,game.getCurrentSetScoreEvolution().get(0));
    }

    @Test
    void aSetShouldBeOverAsSoonAsAPlayerReachesTheWinScore() throws UnknownPlayerException, SetIsAlreadyWonException, GameIsNotOverException{
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        Assertions.assertEquals(8, game.getCurrentSetScoreEvolution().size());
        SetScore expectedNinthEvent = new SetScore(Points.SCORE40, Points.WIN);
        Assertions.assertEquals(expectedNinthEvent,game.getCurrentSetScoreEvolution().get(7));
        Assertions.assertTrue(game.isGameOver());
        Player winner = game.closeGameAndDeclareWinner();
        Assertions.assertEquals(secondPlayer, winner);
    }

    @Test
    public void aSetShouldNotContinueAfterAPlayerHasWon() throws UnknownPlayerException,SetIsAlreadyWonException {
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(secondPlayer);
        Assertions.assertThrows(SetIsAlreadyWonException.class, () -> {
            game.grantPoint(firstPlayer);
        });
    }

    @Test
    public void aWinnerShouldNotBeDeclaredBeforeTheGameIsOver() throws UnknownPlayerException, SetIsAlreadyWonException, GameIsNotOverException{
        game.grantPoint(firstPlayer);
        game.grantPoint(firstPlayer);
        game.grantPoint(secondPlayer);
        game.grantPoint(firstPlayer);
        Assertions.assertThrows(GameIsNotOverException.class, () -> {
            game.closeGameAndDeclareWinner();
        });
    }
}
