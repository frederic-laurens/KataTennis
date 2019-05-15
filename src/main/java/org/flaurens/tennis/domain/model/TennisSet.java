package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.exceptions.GameIsAlreadyWonException;
import org.flaurens.tennis.domain.model.exceptions.SetIsNotOverException;
import org.flaurens.tennis.domain.model.exceptions.UnknownPlayerException;
import org.flaurens.tennis.domain.model.scores.*;
import org.flaurens.tennis.domain.model.scoringevents.FirstPlayerGameScoringEvent;
import org.flaurens.tennis.domain.model.scoringevents.SecondPlayerGameScoringEvent;

import java.util.ArrayList;
import java.util.List;

public class TennisSet {

    private final List<Player> players = new ArrayList<>();

    private SetScore currentSetScore;

    private TieBreakScore currentTieBreakScore;

    private final List<GlobalScore> scoreEvolution = new ArrayList<>();

    private TennisGame currentGame;

    private boolean isSetOver = false;

    private Player setWinner = null;


    public TennisSet(Player firstPlayer, Player secondPlayer){

        this.players.add(firstPlayer);
        this.players.add(secondPlayer);

        currentSetScore = SetScore.initialScore();

    }

    public void startGame(){
        this.currentGame = new TennisGame();
        this.scoreEvolution.add(new GlobalScore(this.currentGame.getCurrentScore(),this.currentSetScore));
    }

    public void grantPoint(Player player) throws UnknownPlayerException, GameIsAlreadyWonException {
        boolean isCurrentGameOver;

        if(players.get(0).equals(player)){
            isCurrentGameOver = this.currentGame.updateGameScore(new FirstPlayerGameScoringEvent());
        } else if (players.get(1).equals(player)){
            isCurrentGameOver = this.currentGame.updateGameScore(new SecondPlayerGameScoringEvent());
        } else {
            throw new UnknownPlayerException("Player unknown : "+player.toString());
        }

        if(isCurrentGameOver){
            this.updateSetScore();
            this.startGame();
        }

        if(currentSetScore.isWinningScore()){
            declareWinner();
        }
    }

    private void updateSetScore(){
        SetScore newSetScore;
        if(Points.WIN.equals(this.currentGame.getCurrentScore().getFirstPlayerPoints())){
            newSetScore = new SetScore(currentSetScore.getFirstPlayerPoints()+1, currentSetScore.getSecondPlayerPoints());
        } else {
            newSetScore = new SetScore(currentSetScore.getFirstPlayerPoints(), currentSetScore.getSecondPlayerPoints()+1);
        }
        this.currentSetScore = newSetScore;
        this.scoreEvolution.add(new GlobalScore(this.currentGame.getCurrentScore(),newSetScore));
    }

    private void declareWinner(){
        this.isSetOver = true;
        if(currentSetScore.getFirstPlayerPoints() > currentSetScore.getSecondPlayerPoints()){
            this.setWinner = this.players.get(0);
        } else {
            this.setWinner = this.players.get(1);
        }
    }

    public List<Score> getCurrentGameScoreEvolution(){
        return currentGame.getScoreEvolution();
    }

    public List<GlobalScore> getScoreEvolution(){ return scoreEvolution;}

    public boolean isSetOver() {
        return isSetOver;
    }

    public Player getWinner() throws SetIsNotOverException {
        if(isSetOver) {
            return this.setWinner;
        } else throw new SetIsNotOverException("Current game score is: "+currentGame.getCurrentScore().toString());
    }


}
