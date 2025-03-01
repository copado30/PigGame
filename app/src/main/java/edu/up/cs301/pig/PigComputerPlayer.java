package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        if(!(info instanceof PigGameState)){return;}

        PigGameState gameState = new PigGameState((PigGameState)info);

        if(gameState.getPlayerId() == this.playerNum){
            Random chance = new Random();
            if(chance.nextBoolean()){
                PigHoldAction holdAction = new PigHoldAction(this);
                super.game.sendAction(holdAction);

            }
            else{
                PigRollAction rollAction = new PigRollAction(this);
                super.game.sendAction(rollAction);
            }
            sleep(2000);
        }

    }//receiveInfo

}
