package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        System.out.println("receiveInfo");
        if(!(info instanceof PigGameState)){
            this.flash(Color.BLACK,2000);
            return;
        }
        PigGameState gameState = new PigGameState((PigGameState)info);
        if(this.playerNum == 0){
            playerScoreTextView.setText("" + gameState.getPlayer0Score());
            oppScoreTextView.setText("" + gameState.getPlayer1Score());
        }
        if(this.playerNum == 1){
            playerScoreTextView.setText("" + gameState.getPlayer1Score());
            oppScoreTextView.setText("" + gameState.getPlayer0Score());
        }
        turnTotalTextView.setText("" + gameState.getDiceAdd());
        if(gameState.getDieVal() == 1){dieImageButton.setImageResource(R.drawable.face1);}
        else if(gameState.getDieVal() == 2){dieImageButton.setImageResource(R.drawable.face2);}
        else if(gameState.getDieVal() == 3){dieImageButton.setImageResource(R.drawable.face3);}
        else if(gameState.getDieVal() == 4){dieImageButton.setImageResource(R.drawable.face4);}
        else if(gameState.getDieVal() == 5){dieImageButton.setImageResource(R.drawable.face5);}
        else if(gameState.getDieVal() == 6){dieImageButton.setImageResource(R.drawable.face6);}

    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        if(button.getId() == dieImageButton.getId()){
            PigRollAction rollAction = new PigRollAction(this);
            super.game.sendAction(rollAction);
        }
        else if(button.getId() == holdButton.getId()){
            PigHoldAction holdAction = new PigHoldAction(this);
            super.game.sendAction(holdAction);
        }

    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

}// class PigHumanPlayer
