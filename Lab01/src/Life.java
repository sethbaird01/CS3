import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
    this is the Controller component
 */

class Life extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private LifeView view;
	private LifeModel model;
	private JButton runButton, pauseButton, resumeButton, resetButton, colorButton;
	private JFileChooser fc;
	public static boolean randomColor = false;

	Life() throws IOException
	{
		super("Conway's Life");

		Object[] options = {"Randomize",
                    "Import file"};
		int n = JOptionPane.showOptionDialog(this,
			"Use random board or import file?",
			"Game of Life",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,     //do not use a custom Icon
			options,  //the titles of buttons
			options[0]); //default button title

			File file = null;

			if(n == 1){
				fc = new JFileChooser();
				fc.showDialog(Life.this, "Attach");
				file = fc.getSelectedFile();		
			}


		// build the buttons
		JPanel controlPanel = 
				new JPanel(new FlowLayout(FlowLayout.CENTER));
		runButton = new JButton("Run");
		runButton.addActionListener(this);
		runButton.setEnabled(true);
		controlPanel.add(runButton);

		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		pauseButton.setEnabled(false);
		controlPanel.add(pauseButton);

		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
		resumeButton.setEnabled(false);
		controlPanel.add(resumeButton);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetButton.setEnabled(false);
		controlPanel.add(resetButton);

		colorButton = new JButton("Randomize Color");
		colorButton.addActionListener(this);
		colorButton.setEnabled(false);
		controlPanel.add(colorButton);

		// build the view
		view = new LifeView();
		view.setBackground(Color.white);

		// put buttons, view together
		Container c = getContentPane();
		c.add(controlPanel, BorderLayout.NORTH);
		c.add(view, BorderLayout.CENTER);

		// build the model
		if(file != null){//file chosen
			model = new LifeModel(view, file.getAbsolutePath());
		}else{//no file
			model = new LifeModel(view, "");
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		if ( b == runButton )
		{
			model.run();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resetButton.setEnabled(true);
			resumeButton.setEnabled(false);
			colorButton.setEnabled(true);
		}
		else if ( b == pauseButton )
		{
			model.pause();
			runButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
		}
		else if ( b == resumeButton )
		{
			model.resume();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
		}
		else if ( b == resetButton )
		{
			runButton.setEnabled(true);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(false);
			colorButton.setEnabled(false);
			Life.randomColor = false;
			//parameters reset

			this.dispose();

			try {
				Life conway = new Life(); //parameterize to start w/ a particular file
						
			conway.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
			conway.setSize(570, 640);
			//conway.show(); //deprecated, added call below
			conway.setVisible(true);
			} catch (IOException ee) {			}


		}
		else if ( b == colorButton )
		{
			Life.randomColor = true;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Life conway = new Life(); //parameterize to start w/ a particular file
		
		conway.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		conway.setSize(570, 640);
		//conway.show(); //deprecated, added call below
		conway.setVisible(true);
	}
}