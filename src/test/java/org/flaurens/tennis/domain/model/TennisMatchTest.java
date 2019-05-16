package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;
import org.flaurens.tennis.domain.model.exceptions.MatchIsNotOverException;
import org.flaurens.tennis.domain.model.exceptions.UnknownPlayerException;
import org.flaurens.tennis.domain.model.scores.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TennisMatchTest {

    private Player firstPlayer = null;

    private Player secondPlayer = null;

    private TennisMatch tennisMatch = null;

    @BeforeEach
    public void createSetAndStartFirstGame(){
        firstPlayer = new Player("A");
        secondPlayer = new Player("B");
        tennisMatch = new TennisMatch(firstPlayer, secondPlayer);
    }

    @Test
    public void aWinnerShouldNotBeDeclaredBeforeSetIsOver() throws GameFlowException {
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(secondPlayer);
        tennisMatch.grantPoint(firstPlayer);
        Assertions.assertThrows(MatchIsNotOverException.class, () -> tennisMatch.getWinner());
    }

    @Test
    void gameShouldHandleUnknownPlayersByNotUpdatingTheScore(){
        Assertions.assertThrows(UnknownPlayerException.class, () -> tennisMatch.grantPoint(new Player("Unknown")));
        Assertions.assertEquals(1, tennisMatch.getCurrentGameScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedFirstScore, tennisMatch.getCurrentScore().getGameScore());
    }

    @Test
    public void aSetShouldStartWithAScoreOf0forEachPlayer(){
        List<GlobalScore> setScoreEvolution = tennisMatch.getScoreEvolution();
        Assertions.assertEquals(1,setScoreEvolution.size());
        Assertions.assertEquals(new SetScore(0,0), tennisMatch.getCurrentScore().getSetScore());
    }

    private void winAGame(Player player) throws GameFlowException{
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
    }

    private void winXGame(int numberOfGames, Player player) throws GameFlowException{
        for(int i=0; i<numberOfGames; i++){
            winAGame(player);
        }
    }

    private void winTieBreak(Player player) throws GameFlowException{
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
        tennisMatch.grantPoint(player);
    }


    @Test
    public void theSetScoreShouldIncrementWhenAGameIsWon() throws GameFlowException{
        winAGame(firstPlayer);
        Assertions.assertEquals(new SetScore(1,0), tennisMatch.getCurrentScore().getSetScore());
    }

    @Test
    public void aSetShouldBeOverWithAScoreOf6To4() throws GameFlowException{
        winXGame(4, firstPlayer);
        winXGame(6, secondPlayer);
        Assertions.assertTrue(tennisMatch.isMatchOver());
        Assertions.assertEquals(tennisMatch.getWinner(), secondPlayer);
    }

    @Test
    public void aSetShouldNotBeOverWithAScoreOf6To5() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        Assertions.assertEquals(new SetScore(5,6), tennisMatch.getCurrentScore().getSetScore());
        Assertions.assertFalse(tennisMatch.isMatchOver());
    }

    @Test
    public void aSetShouldNotBeOverWithAScoreOf6To6() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
        Assertions.assertEquals(new SetScore(6,6), tennisMatch.getCurrentScore().getSetScore());
        Assertions.assertFalse(tennisMatch.isMatchOver());
    }

    @Test
    public void aSetShouldBeOverWithAScoreOf7To6() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winXGame(1, firstPlayer);
        winTieBreak(firstPlayer);
        Assertions.assertEquals(new SetScore(7,6), tennisMatch.getCurrentScore().getSetScore());
        Assertions.assertTrue(tennisMatch.isMatchOver());
        Assertions.assertEquals(tennisMatch.getWinner(), firstPlayer);
    }

    @Test
    public void tieBreakRuleShouldActivateWithAScoreOf6To6() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
        Assertions.assertEquals(new GameScore(Points.SCORE0,Points.SCORE0), tennisMatch.getCurrentScore().getGameScore());
        Assertions.assertEquals(new SetScore(6,6), tennisMatch.getCurrentScore().getSetScore());
        Assertions.assertEquals(new TieBreakScore(0,0), tennisMatch.getCurrentScore().getTieBreakScore());
    }

    @Test void theTieBreakScoreShouldUpdateProperly() throws  GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(secondPlayer);
        Assertions.assertEquals(new GameScore(Points.SCORE0,Points.SCORE0), tennisMatch.getCurrentScore().getGameScore());
        Assertions.assertEquals(new SetScore(6,6), tennisMatch.getCurrentScore().getSetScore());
        Assertions.assertEquals(new TieBreakScore(2,1), tennisMatch.getCurrentScore().getTieBreakScore());
        Assertions.assertFalse(tennisMatch.isMatchOver());
    }

    @Test
    public void theWinnerOfTheTieBreakShouldWinTheMatch() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
        winTieBreak(secondPlayer);
        Assertions.assertEquals(new GameScore(Points.SCORE0,Points.SCORE0), tennisMatch.getCurrentScore().getGameScore());
        Assertions.assertEquals(new SetScore(6,7), tennisMatch.getCurrentScore().getSetScore());
        Assertions.assertEquals(new TieBreakScore(0,0), tennisMatch.getCurrentScore().getTieBreakScore());
        Assertions.assertTrue(tennisMatch.isMatchOver());
        Assertions.assertEquals(tennisMatch.getWinner(), secondPlayer);
    }

    @Test
    public void theGameScoreShouldUpdateWithinAGame() throws GameFlowException{
        winXGame(1, firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(secondPlayer);
        Assertions.assertEquals(new GameScore(Points.SCORE30,Points.SCORE15), tennisMatch.getCurrentScore().getGameScore());
        Assertions.assertEquals(new SetScore(1,0), tennisMatch.getCurrentScore().getSetScore());
    }

    @Test
    public void theGameScoreShouldGetBackTo0to0whenANewSetStarts() throws GameFlowException{
        winXGame(1, firstPlayer);
        Assertions.assertEquals(new GameScore(Points.SCORE0,Points.SCORE0), tennisMatch.getCurrentScore().getGameScore());
        Assertions.assertEquals(new SetScore(1,0), tennisMatch.getCurrentScore().getSetScore());
    }

    @Test
    public void theGameScoreShouldBe0DuringATieBreak() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(firstPlayer);
        tennisMatch.grantPoint(secondPlayer);
        Assertions.assertEquals(new GameScore(Points.SCORE0,Points.SCORE0), tennisMatch.getCurrentScore().getGameScore());
    }
}
