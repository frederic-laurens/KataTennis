package org.flaurens.tennis.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TennisSet {

    private final List<Player> players = new ArrayList<>();

    private SetScore currentSetScore;

    private final List<SetScore> scoreEvolution = new ArrayList<>();

    private TennisGame currentGame;

    private boolean isSetOver = false;

    private Player setWinner = null;


    public TennisSet(Player firstPlayer, Player secondPlayer){

        this.players.add(firstPlayer);
        this.players.add(secondPlayer);

        currentSetScore = SetScore.initialSetScore();
        this.scoreEvolution.add(currentSetScore);
    }

    public void startGame(){
        this.currentGame = new TennisGame();
    }

    public void grantPoint(Player player) throws UnknownPlayerException, GameIsAlreadyWonException {
        boolean isCurrentGameOver;

        if(players.get(0).equals(player)){
            isCurrentGameOver = this.currentGame.updateGameScore(new FirstPlayerScoringEvent());
        } else if (players.get(1).equals(player)){
            isCurrentGameOver = this.currentGame.updateGameScore(new SecondPlayerScoringEvent());
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
        this.scoreEvolution.add(newSetScore);
    }

    private void declareWinner(){
        this.isSetOver = true;
        if(currentSetScore.getFirstPlayerPoints() > currentSetScore.getSecondPlayerPoints()){
            this.setWinner = this.players.get(0);
        } else {
            this.setWinner = this.players.get(1);
        }
    }

    public List<GameScore> getCurrentGameScoreEvolution(){
        return currentGame.getScoreEvolution();
    }

    public List<SetScore> getScoreEvolution(){ return scoreEvolution;}

    public boolean isSetOver() {
        return isSetOver;
    }

    public Player getWinner() throws SetIsNotOverException {
        if(isSetOver) {
            return this.setWinner;
        } else throw new SetIsNotOverException("Current game score is: "+currentGame.getCurrentScore().toString());
    }


}
