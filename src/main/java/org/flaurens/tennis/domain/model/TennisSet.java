package org.flaurens.tennis.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TennisSet {

    private final List<Player> players;

    private TennisGame currentGame;

    private boolean isSetOver;

    private Player setWinner;

    public TennisSet(Player firstPlayer, Player secondPlayer){
        this.players = new ArrayList<>();
        this.players.add(firstPlayer);
        this.players.add(secondPlayer);
        this.isSetOver = false;
        this.setWinner = null;
    }

    public void startSet(){
        this.currentGame = new TennisGame();
    }

    public void grantPoint(Player player) throws UnknownPlayerException, GameIsAlreadyWonException {
        boolean isCurrentSetOver;
        if(players.get(0).equals(player)){
            isCurrentSetOver = this.currentGame.updateGameScore(new FirstPlayerScoringEvent());
        } else if (players.get(1).equals(player)){
            isCurrentSetOver = this.currentGame.updateGameScore(new SecondPlayerScoringEvent());
        } else {
            throw new UnknownPlayerException("Player unknown : "+player.toString());
        }
        if(isCurrentSetOver){
            isSetOver = true;
            if(Points.WIN.equals(this.currentGame.getCurrentScore().getFirstPlayerPoints())){
                this.setWinner = players.get(0);
            } else {
                this.setWinner = players.get(1);
            }
        }
    }

    public List<GameScore> getCurrentSetScoreEvolution(){
        return currentGame.getScoreEvolution();
    }

    public boolean isSetOver() {
        return isSetOver;
    }

    public Player finishSetAndDeclareWinner() throws GameIsNotOverException{
        if(isSetOver) {
            return this.setWinner;
        } else throw new GameIsNotOverException();
    }
}
