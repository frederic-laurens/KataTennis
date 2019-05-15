package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;
import org.flaurens.tennis.domain.model.exceptions.SetIsNotOverException;
import org.flaurens.tennis.domain.model.exceptions.UnknownPlayerException;
import org.flaurens.tennis.domain.model.scores.GameScore;
import org.flaurens.tennis.domain.model.scores.GlobalScore;
import org.flaurens.tennis.domain.model.scores.Points;
import org.flaurens.tennis.domain.model.scores.SetScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TennisSetTest {

    private Player firstPlayer = null;

    private Player secondPlayer = null;

    private TennisSet tennisSet = null;

    @BeforeEach
    public void createSetAndStartFirstGame(){
        firstPlayer = new Player("A");
        secondPlayer = new Player("B");
        tennisSet = new TennisSet(firstPlayer, secondPlayer);
        tennisSet.startGame();
    }

    @Test
    public void aWinnerShouldNotBeDeclaredBeforeSetIsOver() throws GameFlowException {
        tennisSet.grantPoint(firstPlayer);
        tennisSet.grantPoint(firstPlayer);
        tennisSet.grantPoint(secondPlayer);
        tennisSet.grantPoint(firstPlayer);
        Assertions.assertThrows(SetIsNotOverException.class, () -> tennisSet.getWinner());
    }

    @Test
    void gameShouldHandleUnknownPlayersByNotUpdatingTheScore(){
        Assertions.assertThrows(UnknownPlayerException.class, () -> tennisSet.grantPoint(new Player("Unknown")));
        Assertions.assertEquals(1, tennisSet.getCurrentGameScoreEvolution().size());
        GameScore expectedFirstScore = new GameScore(Points.SCORE0, Points.SCORE0);
        Assertions.assertEquals(expectedFirstScore, tennisSet.getCurrentGameScoreEvolution().get(0));
    }

    @Test
    public void aSetShouldStartWithAScoreOf0forEachPlayer(){
        List<GlobalScore> setScoreEvolution = tennisSet.getScoreEvolution();
        Assertions.assertEquals(1,setScoreEvolution.size());
        Assertions.assertEquals(new SetScore(0,0), setScoreEvolution.get(0).getSetScore());
    }

    private void winAGame(Player player) throws GameFlowException{
        tennisSet.grantPoint(player);
        tennisSet.grantPoint(player);
        tennisSet.grantPoint(player);
        tennisSet.grantPoint(player);
    }

    private void winXGame(int numberOfGames, Player player) throws GameFlowException{
        for(int i=0; i<numberOfGames; i++){
            winAGame(player);
        }
    }

    @Test
    public void theSetScoreShouldIncrementWhenAGameIsWon() throws GameFlowException{
        winAGame(firstPlayer);
        List<GlobalScore> setScoreEvolution = tennisSet.getScoreEvolution();
        Assertions.assertEquals(3,setScoreEvolution.size());
        Assertions.assertEquals(new SetScore(1,0), setScoreEvolution.get(2).getSetScore());
    }

    @Test
    public void aSetShouldBeOverWithAScoreOf6To4() throws GameFlowException{
        winXGame(4, firstPlayer);
        winXGame(6, secondPlayer);
        Assertions.assertTrue(tennisSet.isSetOver());
        Assertions.assertEquals(tennisSet.getWinner(), secondPlayer);
    }

    @Test
    public void aSetShouldNotBeOverWithAScoreOf6To5() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        Assertions.assertEquals(23,tennisSet.getScoreEvolution().size());
        Assertions.assertEquals(new SetScore(5,6),tennisSet.getScoreEvolution().get(22).getSetScore());
        Assertions.assertFalse(tennisSet.isSetOver());
    }

    @Test
    public void aSetShouldNotBeOverWithAScoreOf6To6() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
        Assertions.assertEquals(25,tennisSet.getScoreEvolution().size());
        Assertions.assertEquals(new SetScore(6,6),tennisSet.getScoreEvolution().get(24).getSetScore());
        Assertions.assertFalse(tennisSet.isSetOver());
    }

    @Test
    public void aSetShouldBeOverWithAScoreOf7To6() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winXGame(2, firstPlayer);
        Assertions.assertEquals(27,tennisSet.getScoreEvolution().size());
        Assertions.assertEquals(new SetScore(7,6),tennisSet.getScoreEvolution().get(26).getSetScore());
        Assertions.assertTrue(tennisSet.isSetOver());
        Assertions.assertEquals(tennisSet.getWinner(), firstPlayer);
    }

    @Test
    public void tieBreakRuleShouldActivateWithAScoreOf6To6() throws GameFlowException{
        winXGame(5, firstPlayer);
        winXGame(6, secondPlayer);
        winAGame(firstPlayer);
    }
}
