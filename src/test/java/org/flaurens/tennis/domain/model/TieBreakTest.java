package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;
import org.flaurens.tennis.domain.model.scores.TieBreakScore;
import org.flaurens.tennis.domain.model.scoringevents.FirstPlayerTieBreakScoringEvent;
import org.flaurens.tennis.domain.model.scoringevents.SecondPlayerTieBreakScoringEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TieBreakTest {

    @Test
    public void aTieBreakShouldStartWithAScoreOf0forEachPlayer(){
        TieBreak tieBreak = new TieBreak();
        Assertions.assertEquals(new TieBreakScore(0,0), tieBreak.getCurrentScore());
    }

    @Test
    public void FirstPlayerShouldBeAbleToScorePointDuringATiebreak() throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(1,0), tieBreak.getCurrentScore());
    }

    @Test
    public void SecondPlayerShouldBeAbleToScorePointDuringATiebreak() throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(0,1), tieBreak.getCurrentScore());
    }


    @Test
    public void FirstPlayerShouldBeAbleToScoreSeveralPointsDuringATiebreak() throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(2,0), tieBreak.getCurrentScore());
    }

    @Test
    public void ATieBreakShouldBeOverWithAScoreOf6to0() throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(6,0), tieBreak.getCurrentScore());
        Assertions.assertFalse(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldBeOverWithAScoreOf7to5()throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(7,5), tieBreak.getCurrentScore());
        Assertions.assertTrue(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldNotBeOverWithAScoreOf7to6() throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(7,6), tieBreak.getCurrentScore());
        Assertions.assertFalse(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldNotBeOverWithAScoreOf9to8()throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(9,8), tieBreak.getCurrentScore());
        Assertions.assertFalse(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldBeOverWithAScoreOf10to8()throws GameFlowException{
        TieBreak tieBreak = new TieBreak();
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new SecondPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        tieBreak.updateGameScore(new FirstPlayerTieBreakScoringEvent());
        Assertions.assertEquals(new TieBreakScore(10,8), tieBreak.getCurrentScore());
        Assertions.assertTrue(tieBreak.isOver);
    }
}
