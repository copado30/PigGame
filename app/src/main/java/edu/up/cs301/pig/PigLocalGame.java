package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    /**
     * This ctor creates a new game state
     */
    private PigGameState gameState;

    public PigLocalGame() {
        //TODO  You will implement this constructor
        gameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == gameState.getPlayerId()){
            return true;
        }
        return false;
    }
    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method

        if(action instanceof PigHoldAction){

          if(gameState.getPlayerId() == 0){
              gameState.setPlayer0Score(gameState.getPlayer0Score() + gameState.getDiceAdd());
              gameState.setPlayerId(1);
          }
          else if(gameState.getPlayerId() == 1) {
              gameState.setPlayer1Score(gameState.getPlayer1Score() + gameState.getDiceAdd());
              gameState.setPlayerId(0);
          }
          return true;
        }//end of the holdAction
        else if(action instanceof PigRollAction){
            Random rollDice = new Random();
            gameState.setDieVal(rollDice.nextInt(6)+1);

            if(gameState.getDieVal()==1){

                if(gameState.getPlayerId() == 0){
                    gameState.setDiceAdd(0);
                    gameState.setPlayerId(1);
                }
                else if(gameState.getPlayerId() == 1) {
                    gameState.setDiceAdd(0);
                    gameState.setPlayerId(0);
                }
            }//end of die = 1 scenario
            else{
                if(gameState.getPlayerId() == 0){
                    gameState.setDiceAdd(gameState.getDiceAdd()+ gameState.getDieVal());
                    gameState.setPlayerId(1);
                }
                else if(gameState.getPlayerId() == 1) {
                    gameState.setDiceAdd(gameState.getDiceAdd()+ gameState.getDieVal());
                    gameState.setPlayerId(0);
                }
            }//end of if dieVal > 1
            return true;
        }//end of rollAction

        return false;// and do nothing
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState pigGameState = new PigGameState(gameState);
        p.sendInfo(pigGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        return null;
    }

}// class PigLocalGame
