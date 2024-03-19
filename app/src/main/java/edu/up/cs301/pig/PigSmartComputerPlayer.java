package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

public class PigSmartComputerPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PigSmartComputerPlayer(String name) { super(name);}

    @Override
    protected void receiveInfo(GameInfo info) {

        if (!(info instanceof PigGameState)) {
            return;
        }

        PigGameState gameState = new PigGameState((PigGameState) info);

        if (gameState.getPlayerId() == this.playerNum) {

            if (gameState.getPlayerId() == 0) {//if it's the computers turn and hes the 0 player
                int myScore = ((PigGameState) info).getPlayer0Score();
                int currentAdd = ((PigGameState) info).getDiceAdd();

                if(((PigGameState) info).getPlayer1Score() == 0 && currentAdd > 10){
                    PigHoldAction holdAction = new PigHoldAction(this);
                    super.game.sendAction(holdAction);
                }
                else if (((PigGameState) info).getPlayer1Score() > myScore + currentAdd) {

                    if(currentAdd > 15 && ((PigGameState) info).getPlayer1Score() + 10 >= 50){
                        PigHoldAction holdAction = new PigHoldAction(this);
                        super.game.sendAction(holdAction);
                    }
                    PigRollAction rollAction = new PigRollAction(this);
                    super.game.sendAction(rollAction);
                }
                else if (currentAdd > 8) {
                    PigHoldAction holdAction = new PigHoldAction(this);
                    super.game.sendAction(holdAction);
                }
            }
            else if (gameState.getPlayerId() == 1) {//if it's the computers turn and hes player 1

                int myScore = ((PigGameState) info).getPlayer1Score();
                int currentAdd = ((PigGameState) info).getDiceAdd();

                if(((PigGameState) info).getPlayer0Score() == 0 && currentAdd > 10){
                    PigHoldAction holdAction = new PigHoldAction(this);
                    super.game.sendAction(holdAction);
                }
                else if (((PigGameState) info).getPlayer0Score() > myScore + currentAdd) {
                    if(currentAdd > 15 && ((PigGameState) info).getPlayer0Score() + 10 >= 50){
                        PigHoldAction holdAction = new PigHoldAction(this);
                        super.game.sendAction(holdAction);
                    }
                    PigRollAction rollAction = new PigRollAction(this);
                    super.game.sendAction(rollAction);
                }
                else if (currentAdd > 8) {
                    PigHoldAction holdAction = new PigHoldAction(this);
                    super.game.sendAction(holdAction);
                }

            }
        }
        sleep(1700);
    }
}
