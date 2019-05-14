package org.flaurens.tennis.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TennisGame {

    private final List<Player> players;

    private TennisSet currentSet;

    private boolean isGameOver;

    private Player overallGameWinner;

    public TennisGame(Player firstPlayer, Player secondPlayer){
        this.players = new ArrayList<>();
        this.players.add(firstPlayer);
        this.players.add(secondPlayer);
        this.isGameOver = false;
        this.overallGameWinner = null;
    }

    public void startSet(){
        this.currentSet = new TennisSet();
    }

    public void grantPoint(Player player) throws UnknownPlayerException, SetIsAlreadyWonException {
        boolean isCurrentSetOver;
        if(players.get(0).equals(player)){
            isCurrentSetOver = this.currentSet.updateSetScore(new FirstPlayerScoringEvent());
        } else if (players.get(1).equals(player)){
            isCurrentSetOver = this.currentSet.updateSetScore(new SecondPlayerScoringEvent());
        } else {
            throw new UnknownPlayerException("Player unknown : "+player.toString());
        }
        if(isCurrentSetOver){
            isGameOver = true;
            if(Points.WIN.equals(this.currentSet.getCurrentScore().getFirstPlayerPoints())){
                this.overallGameWinner = players.get(0);
            } else {
                this.overallGameWinner = players.get(1);
            }
        }
    }

    public List<SetScore> getCurrentSetScoreEvolution(){
        return currentSet.getScoreEvolution();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Player closeGameAndDeclareWinner() throws GameIsNotOverException{
        if(isGameOver) {
            return this.overallGameWinner;
        } else throw new GameIsNotOverException();
    }
}
