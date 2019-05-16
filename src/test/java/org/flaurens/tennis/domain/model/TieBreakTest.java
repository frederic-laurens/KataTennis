package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameFlowException;
import org.flaurens.tennis.domain.model.scores.TieBreakScore;
import org.flaurens.tennis.domain.model.scoringevents.ScoringEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TieBreakTest {

    private TieBreak tieBreak;

    @BeforeEach
    private void createTieBreak(){
        tieBreak = new TieBreak();
    }

    private void firstPlayerWinsXTimes(int timesWon) throws GameFlowException{
        for(int i=0; i<timesWon; i++){
            tieBreak.updateGameScore(ScoringEvent.firstPlayerWins());
        }
    }

    private void secondPlayerWinsXTimes(int timesWon) throws GameFlowException{
        for(int i=0; i<timesWon; i++){
            tieBreak.updateGameScore(ScoringEvent.secondPlayerWins());
        }
    }

    @Test
    public void aTieBreakShouldStartWithAScoreOf0forEachPlayer(){
        Assertions.assertEquals(new TieBreakScore(0,0), tieBreak.getCurrentScore());
    }

    @Test
    public void FirstPlayerShouldBeAbleToScorePointDuringATiebreak() throws GameFlowException{
        firstPlayerWinsXTimes(1);
        Assertions.assertEquals(new TieBreakScore(1,0), tieBreak.getCurrentScore());
    }

    @Test
    public void SecondPlayerShouldBeAbleToScorePointDuringATiebreak() throws GameFlowException{
        secondPlayerWinsXTimes(1);
        Assertions.assertEquals(new TieBreakScore(0,1), tieBreak.getCurrentScore());
    }


    @Test
    public void FirstPlayerShouldBeAbleToScoreSeveralPointsDuringATiebreak() throws GameFlowException{
        firstPlayerWinsXTimes(2);
        Assertions.assertEquals(new TieBreakScore(2,0), tieBreak.getCurrentScore());
    }

    @Test
    public void ATieBreakShouldBeOverWithAScoreOf6to0() throws GameFlowException{
        firstPlayerWinsXTimes(6);
        Assertions.assertEquals(new TieBreakScore(6,0), tieBreak.getCurrentScore());
        Assertions.assertFalse(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldBeOverWithAScoreOf7to5()throws GameFlowException{
        firstPlayerWinsXTimes(6);
        secondPlayerWinsXTimes(5);
        firstPlayerWinsXTimes(1);
        Assertions.assertEquals(new TieBreakScore(7,5), tieBreak.getCurrentScore());
        Assertions.assertTrue(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldNotBeOverWithAScoreOf7to6() throws GameFlowException{
        firstPlayerWinsXTimes(6);
        secondPlayerWinsXTimes(6);
        firstPlayerWinsXTimes(1);
        Assertions.assertEquals(new TieBreakScore(7,6), tieBreak.getCurrentScore());
        Assertions.assertFalse(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldNotBeOverWithAScoreOf9to8()throws GameFlowException{
        firstPlayerWinsXTimes(6);
        secondPlayerWinsXTimes(6);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        Assertions.assertEquals(new TieBreakScore(9,8), tieBreak.getCurrentScore());
        Assertions.assertFalse(tieBreak.isOver);
    }

    @Test
    public void ATieBreakShouldBeOverWithAScoreOf10to8()throws GameFlowException{
        firstPlayerWinsXTimes(6);
        secondPlayerWinsXTimes(6);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        secondPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        firstPlayerWinsXTimes(1);
        Assertions.assertEquals(new TieBreakScore(10,8), tieBreak.getCurrentScore());
        Assertions.assertTrue(tieBreak.isOver);
    }
}
