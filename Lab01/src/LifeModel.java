import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == "" ) //use random population
		{ 			System.out.println("randomizing: "+fileName);
                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{          		
			//System.out.println("parsing file: "+fileName);
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	
	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();

		myView.updateView(grid);
	}

	public int getNeighbors(int x, int y) {
		int temp = 0;

		try {if(this.grid[x-1][y-1].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		try {if(this.grid[x-1][y].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		try {if(this.grid[x-1][y+1].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		try {if(this.grid[x][y-1].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		//current

		try { if(this.grid[x][y+1].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}
		try {if(this.grid[x+1][y-1].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		try { if(this.grid[x+1][y].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		try {if(this.grid[x+1][y+1].isAliveNow()){
			temp++;
        }		} catch (Exception e) {}

		return temp;
    }

	public int getDeadNeighbors(int x, int y) {
		int temp = 0;
	
		try {if(!this.grid[x-1][y-1].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		try {if(!this.grid[x-1][y].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		try {if(!this.grid[x-1][y+1].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		try {if(!this.grid[x][y-1].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		//current
	
		try { if(!this.grid[x][1].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
		try {if(!this.grid[1][y-1].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		try { if(!this.grid[1][y].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		try {if(!this.grid[x+1][y+1].isAliveNow()){
			temp++;
		}		} catch (Exception e) {}
	
		return temp;
	}
	
	
	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		//settings future lifestates
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if(this.grid[i][j].isAliveNow()){//alive
					switch (this.getNeighbors(i, j)) {
						case 0: //loneliness
						this.grid[i][j].setAliveNext(false); //dies
						break;
						
						case 1: //loneliness
						this.grid[i][j].setAliveNext(false); //dies
						break;
					
						case 2: //survival
					    this.grid[i][j].setAliveNext(true); //lives
						break;

						case 3: //survival
					    this.grid[i][j].setAliveNext(true); //lives
						break;

						case 4: //overcrowding
						this.grid[i][j].setAliveNext(false); //dies
						break;

						case 5: //overcrowding
						this.grid[i][j].setAliveNext(false); //dies
						break;

						case 6: //overcrowding
					    this.grid[i][j].setAliveNext(false); //dies
						break;

						case 7: //overcrowding
						this.grid[i][j].setAliveNext(false); //dies
						break;
						
						case 8: //overcrowding
						this.grid[i][j].setAliveNext(false); //dies
						break;
					
					}
				}
				else{//dead
					if(this.getNeighbors(i, j) == 3){
						//birth
						this.grid[i][j].setAliveNext(true);
					}
				}
			}
		}

		//move nextstates to now
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j].setAliveNow(this.grid[i][j].isAliveNext());
				this.grid[i][j].setAliveNext(false);
			}
		}
	}
}

