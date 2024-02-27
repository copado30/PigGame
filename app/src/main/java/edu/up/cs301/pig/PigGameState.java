package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState{
    private int playerId;
    private int player0Score;
    private int player1Score;
    private int diceAdd;
    private int dieVal;

    public PigGameState(){
        player0Score = 0;
        player1Score = 0;
        playerId = 0;
        diceAdd =0;
        dieVal = 1;
    }
    public PigGameState(PigGameState state){

        this.playerId = state.playerId;
        this.player0Score = state.player0Score;
        this.player1Score = state.player1Score;
        this.diceAdd = state.diceAdd;
        this.dieVal = state.dieVal;
    }

    public int getPlayer0Score() {
        return player0Score;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public int getDiceAdd() {
        return diceAdd;
    }

    public void setDiceAdd(int diceAdd) {
        this.diceAdd = diceAdd;
    }

    public int getDieVal() {
        return dieVal;
    }

    public void setDieVal(int dieVal) {
        this.dieVal = dieVal;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
