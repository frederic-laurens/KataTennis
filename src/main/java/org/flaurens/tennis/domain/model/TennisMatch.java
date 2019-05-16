package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GamePhaseIsAlreadyWonException;
import org.flaurens.tennis.domain.model.exceptions.MatchIsNotOverException;
import org.flaurens.tennis.domain.model.exceptions.UnknownPlayerException;
import org.flaurens.tennis.domain.model.scores.*;
import org.flaurens.tennis.domain.model.scoringevents.*;

import java.util.ArrayList;
import java.util.List;

public class TennisMatch {

    private final List<Player> players = new ArrayList<>();

    private SetScore currentSetScore;

    private final List<GlobalScore> scoreEvolution = new ArrayList<>();

    private TennisPhase currentGame;

    private boolean isMatchOver = false;

    private boolean isTieBreakPhase = false;

    private Player matchWinner = null;

    public TennisMatch(Player firstPlayer, Player secondPlayer){
        this.players.add(firstPlayer);
        this.players.add(secondPlayer);
        currentSetScore = SetScore.initialScore();
        this.startGame();
    }

    private void startGame(){
        this.currentGame = new TennisGame();
        this.isTieBreakPhase = false;
        this.scoreEvolution.add(new GlobalScore(this.currentGame.getCurrentScore(),this.currentSetScore));
    }

    private void startTieBreak(){
        this.currentGame = new TieBreak();
        this.isTieBreakPhase = true;
        this.scoreEvolution.add(new GlobalScore(GameScore.initialScore(),this.currentSetScore,this.currentGame.getCurrentScore()));
    }

    public GlobalScore getCurrentScore(){
        return this.scoreEvolution.get(this.scoreEvolution.size()-1);
    }

    private void declareWinner(){
        this.isMatchOver = true;
        if(currentSetScore.getFirstPlayerPoints() > currentSetScore.getSecondPlayerPoints()){
            this.matchWinner = this.players.get(0);
        } else {
            this.matchWinner = this.players.get(1);
        }
    }

    public List<Score> getCurrentGameScoreEvolution(){
        return currentGame.getScoreEvolution();
    }

    public List<GlobalScore> getScoreEvolution(){ return scoreEvolution;}

    public Player getWinner() throws MatchIsNotOverException {
        if(isMatchOver) {
            return this.matchWinner;
        } else throw new MatchIsNotOverException("Current game score is: "+currentGame.getCurrentScore().toString());
    }

    public boolean isMatchOver() {
        return isMatchOver;
    }

    private SetScore updateSetScore(){
        SetScore newSetScore;
        if(this.currentGame.isFirstPlayerWinning()){
            newSetScore = new SetScore(currentSetScore.getFirstPlayerPoints()+1, currentSetScore.getSecondPlayerPoints());
        } else {
            newSetScore = new SetScore(currentSetScore.getFirstPlayerPoints(), currentSetScore.getSecondPlayerPoints()+1);
        }
        this.currentSetScore = newSetScore;

        return newSetScore;
    }

    private void updateMatchState(boolean isCurrentPhaseOver){
        if(isCurrentPhaseOver){
            SetScore newSetScore = this.updateSetScore();
            if(isTieBreakPhase){
                this.scoreEvolution.add(new GlobalScore(GameScore.initialScore(),newSetScore,TieBreakScore.initialScore()));
            } else {
                this.scoreEvolution.add(new GlobalScore(this.currentGame.getCurrentScore(),newSetScore));
                if(newSetScore.isTieBreakScore()) {
                    this.startTieBreak();
                } else {
                    this.startGame();
                }
            }
        } else {
            if(isTieBreakPhase){
                this.getScoreEvolution().add(new GlobalScore(GameScore.initialScore(), currentSetScore, this.currentGame.getCurrentScore()));
            } else {
                this.getScoreEvolution().add(new GlobalScore(this.currentGame.getCurrentScore(), currentSetScore, GameScore.initialScore()));
            }
        }

        if(currentSetScore.isWinningScore()){
            declareWinner();
        }
    }

    public void grantPoint(Player player) throws UnknownPlayerException, GamePhaseIsAlreadyWonException {
        boolean isCurrentGameOver;

        if(players.get(0).equals(player)){
            isCurrentGameOver = this.currentGame.updateGameScore(new ScoringEvent(true));
        } else if (players.get(1).equals(player)){
            isCurrentGameOver = this.currentGame.updateGameScore(new ScoringEvent(false));
        } else {
            throw new UnknownPlayerException("Player unknown : "+player.toString());
        }

        updateMatchState(isCurrentGameOver);
    }
}
