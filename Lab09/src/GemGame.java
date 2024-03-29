/*************************************************************************
 * Name        : Keith Vertanen 
 * Username    : kvertanen
 * Description : Gem Matching game.  Two players alternate choosing 
 *               where to place the next gem.  Blocks of the same color
 *               get a score multiplier equal to the number of gems in 
 *               that block.  The game is over when either players row
 *               reaches 16 gems.  
 *************************************************************************/

//extras:

//done: display player names
//--done: dialog boxes
//--done: substitute texts with p1 and p2 vars

//done: wild piece
//--done: wild piece image asset
//--done: wild piece less chance to roll
//--done: wild piece score calculation

//done: shot clock
//--done: asynchronous thread running the timer
//--done: timeout to main thread interruption
//--done: difficulties UI integration
//--done: difficulty chooser dialog
//--done: timer UI integration
//--done: drop piece in current player's gemList upon tdOut
//--done: pause button asset
//--done: play button asset
//--done: pause/play functionality

//done winner icon (right aligned)

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GemGame {
	// The maximum number of gems that will fit across the screen
	static final int MAX_GEMS = 16;

	// dynamic variables to be accessed and modified by Timer Runnable

	// tdOut - true when time runs to 0, and is reset after timeout actions finish
	public static boolean tdOut = false;
	// dur - current time, gets decremented by Timer
	public static int dur;
	// init - true when game is done loading for the first time - triggers time
	public static boolean init = false;
	// paused - i'm not explaining this one
	public static boolean paused = false;

	// Given a gem's 0-based index, calculate its x-position
	// in a unit box coordinate system.
	public static double indexToX(int i) {
		return (0.5 + i) * (1.0 / MAX_GEMS);
	}

	// Given a mouse x-position, figure out what gem index
	// we should insert before.
	public static int xToIndex(double x) {
		return (int) ((x + 0.5 / MAX_GEMS) / (1.0 / MAX_GEMS));
	}

	public static void main(String[] args) {
		// Constants that define how we draw various things on the screen
		final double PLAYER1_Y = 0.7;
		final double PLAYER2_Y = 0.3;
		final double PLAYER_HALF_HEIGHT = 0.15;
		final double TEXT_HEIGHT = 0.05;
		final double SCORE_Y = 0.95;
		final double INDICATOR_X = -0.025;
		final double INDICATOR_RADIUS = 0.01;
		final double PAUSE_X = 0.95;
		final double PAUSE_Y = 0.95;
		final double PAUSE_RADIUS = (1.0/32.0);

		// Create a List ADT for each of the two players
		GemList player1 = new GemList();
		GemList player2 = new GemList();

		Gem current = null;
		boolean mouseDown = false;
		boolean turn1 = true;

		int score1 = 0;
		int score2 = 0;

		JFrame frame = new JFrame();

		// Input dialog with a combo box
		// last 3 args are icon, T choices[], and T defaultChoice
		String p1 = (String) JOptionPane.showInputDialog(frame, "Enter Player 1's Name", "Dialog 1",
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		String p2 = (String) JOptionPane.showInputDialog(frame, "Enter Player 2's Name", "Dialog 2",
				JOptionPane.QUESTION_MESSAGE, null, null, null);

		final String[] modes = { "Easy", "Normal", "Blitz" };
		final int[] durs = { 10, 5, 2 };
		final String text = "Select Difficulty:\n \nEasy (10 sec / turn)\nNormal (5 sec / turn)\nBlitz (2 sec / turn)";

		String mode = ((String) JOptionPane.showInputDialog(frame, text, "Dialog 3", JOptionPane.QUESTION_MESSAGE, null,
				modes, modes[1]));

		if (p1 == null || p1.equals("")) {// edge case if user clicks 'cancel' on dialog box
			p1 = "Player 1";
		}

		if (p2 == null || p2.equals("")) {// edge case if user clicks 'cancel' on dialog box
			p2 = "Player 2";
		}

		if (mode == null || mode.equals("")) {// edge case if user clicks 'cancel' on dialog box
			mode = "Normal";
		}

		final int chosenDur = durs[Arrays.asList(modes).indexOf(mode)];

		Thread timeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						System.out.println(dur+" "+tdOut);

						if(!init){
							dur = chosenDur;
						} else

							//important to use && here because it won't evaulate --dur and this advance time
							if (!paused && init && tdOut == false && --dur < 0) {
								dur = chosenDur;
								tdOut = true;
							}
					}
				}, 0, 1000);
			}
		});
		// thread starts, program thread continues on to game loop
		// while thread is running it will continually countdown, setting tdOut to
		// true briefly, signaling game thread to place gem and swap turns. then
		// it sets back to false and counts down once more

		timeThread.start();

		// Game continues until we fill up the entire row of gems
		while ((player1.getSize() < MAX_GEMS) && (player2.getSize() < MAX_GEMS)) {
			StdDraw.clear();
			double mouseX = StdDraw.mouseX();
			double mouseY = StdDraw.mouseY();

			if(paused){//this uses 100% cpu and i'm not sure sure why

				if (StdDraw.mousePressed()) {
					mouseDown = true;
				} else if (mouseDown) {
					//see if they un-paused
					if((mouseY < PAUSE_Y + PAUSE_RADIUS) && (mouseY > PAUSE_Y - PAUSE_RADIUS) && (mouseX < PAUSE_X + PAUSE_RADIUS) && (mouseX > PAUSE_X - PAUSE_RADIUS)){
						
					System.out.println("play button pressed");
					paused = false;
					mouseDown = false; // this will have to be here since continue; is used
					continue;
					}
				}

				//draw paused UI

				StdDraw.picture(PAUSE_X, PAUSE_Y, Gem.imageParentFolder + "play.png");


				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledRectangle(0.5, 0.5, 0.35, 0.35);

	
				// Display the player labels plus their current score
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 50));
				StdDraw.text(0.5, 0.5, "Paused");

				StdDraw.show(10);

			}
			else{
	
				//game done initializing
				//this is necessary because stddraw runs slower on some machines, meaning the time will be wasted on clearing the window
				init = true;
	
				if(current == null){
					current = new Gem();
				}
	
				if (tdOut) {
					
					// this will place the gem in whichever section is hovered,
					// can be reenabled on preference
					///////////// begin//////////
	
					// See if they hovered in the Player1 area
					// if ((mouseY > PLAYER1_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER1_Y + PLAYER_HALF_HEIGHT)) {
					// 	player1.insertBefore(current, xToIndex(mouseX));
					// 	current = null;
					// 	turn1 = !turn1;
					// }
					// // See if they hovered in the Player2 area
					// else if ((mouseY > PLAYER2_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER2_Y + PLAYER_HALF_HEIGHT)) {
					// 	player2.insertBefore(current, xToIndex(mouseX));
					// 	current = null;
					// 	turn1 = !turn1;
					// }
					// tdOut = false;
					// if mouse is in grey area (not in playspace), the gem will be discarded. this
					// may or may not be intentional design
	
					///////////// end//////////
	
					// this will place the block in the current player's gemList
					// enabled by default
					//////////// begin///////////
					System.out.println("tdout true, placing gem");
	
					if (turn1) {// p1's turn
						player1.insertBefore(current, 100);// end of gemList
						current = null;
						turn1 = !turn1;
					} else {// p2's turn
						player2.insertBefore(current, 100);// end of gemList
						current = null;
						turn1 = !turn1;
					}
					tdOut = false;
	
					///////////// end//////////
	
				} else
	
				// Check for a click of the mouse, wait for release of the button
	
				if (StdDraw.mousePressed()) {
					mouseDown = true;
				} else if (mouseDown) {
					//see if they paused
					if((mouseY < PAUSE_Y + PAUSE_RADIUS) && (mouseY > PAUSE_Y - PAUSE_RADIUS) && (mouseX < PAUSE_X + PAUSE_RADIUS) && (mouseX > PAUSE_X - PAUSE_RADIUS)){

						paused = true;
						mouseDown = false; // this will have to be here since continue; is used
						System.out.println("pause button pressed");


						continue;
					} else
					// See if they clicked in the Player1 area
					if ((mouseY > PLAYER1_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER1_Y + PLAYER_HALF_HEIGHT)) {
						player1.insertBefore(current, xToIndex(mouseX));
						current = null;
						turn1 = !turn1;
						dur = chosenDur; // reset shotclock
					}
					// See if they clicked in the Player2 area
					else if ((mouseY > PLAYER2_Y - PLAYER_HALF_HEIGHT) && (mouseY < PLAYER2_Y + PLAYER_HALF_HEIGHT)) {
						player2.insertBefore(current, xToIndex(mouseX));
						current = null;
						turn1 = !turn1;
						dur = chosenDur; // reset shotclock
					}
					// Regardless, this click is over even if we didn't drop the gem
	
					mouseDown = false;
				}
	
				score1 = player1.score();
				score2 = player2.score();
	
				StdDraw.picture(PAUSE_X, PAUSE_Y, Gem.imageParentFolder + "pause.png");

				// Background to show each player
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledRectangle(0.5, PLAYER2_Y, 1.0, PLAYER_HALF_HEIGHT);
				StdDraw.filledRectangle(0.5, PLAYER1_Y, 1.0, PLAYER_HALF_HEIGHT);
	
				// Display the player labels plus their current score
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.setFont(new Font("SansSerif", Font.BOLD, 16));
				StdDraw.textLeft(0.0, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, (p1 + ": ") + score1);
				StdDraw.textLeft(0.0, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, (p2 + ": ") + score2);
	
				// shot clock and display
				StdDraw.setPenColor(Color.BLACK);
				if (dur == 0) {
					StdDraw.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 16));
					StdDraw.textLeft(0.0, PLAYER_HALF_HEIGHT - TEXT_HEIGHT, ("Time Left: " + dur));
	
				} else {
					StdDraw.setFont(new Font("SansSerif", Font.ITALIC, 16));
					StdDraw.textLeft(0.0, PLAYER_HALF_HEIGHT - TEXT_HEIGHT, ("Time Left: " + dur));
	
				}
	
				// difficulty mode display
				StdDraw.setFont(new Font("SansSerif", Font.ITALIC, 16));
				StdDraw.textLeft(0.0, PLAYER_HALF_HEIGHT - (TEXT_HEIGHT * 2), ("Difficulty: " + mode));
	
				// just in case future printing
				StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
	
				// Draw a little blue dot to indicate whose turn it is
				StdDraw.setPenColor(StdDraw.BLUE);
				if (turn1)
					StdDraw.filledCircle(INDICATOR_X, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
				else
					StdDraw.filledCircle(INDICATOR_X, PLAYER2_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, INDICATOR_RADIUS);
	
				// Draw the gems and then any gem currently being dragged around
				player1.draw(PLAYER1_Y);
				player2.draw(PLAYER2_Y);
	
				// See if we currently have a gem waiting to be dropped
				//also checks if hovering over pause button 
				if (current != null && !((mouseY < PAUSE_Y + PAUSE_RADIUS) && (mouseY > PAUSE_Y - PAUSE_RADIUS) && (mouseX < PAUSE_X + PAUSE_RADIUS) && (mouseX > PAUSE_X - PAUSE_RADIUS))){
					current.draw(mouseX, mouseY);
				}
	
				StdDraw.show(25); //slower update for static page
			}
		}

		// Display the final message saying who won the game
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
		if (score1 == score2) {
			StdDraw.text(0.5, SCORE_Y, "Tie game!");
			StdDraw.picture(0.5, SCORE_Y - TEXT_HEIGHT, Gem.imageParentFolder + "crown.png");
		} else if (score1 > score2) {
			StdDraw.text(0.5, SCORE_Y, p1 + " wins!");
			StdDraw.picture(0.95, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, Gem.imageParentFolder + "crown.png");
		} else {
			StdDraw.text(0.5, SCORE_Y, p2 + " wins!");
			StdDraw.picture(0.95, PLAYER1_Y + PLAYER_HALF_HEIGHT - TEXT_HEIGHT, Gem.imageParentFolder + "crown.png");
		}

		StdDraw.show(0);
	}
}