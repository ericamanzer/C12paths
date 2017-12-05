/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.util.*;

public class ControlGUI extends JPanel {
	//private JTextField name; 
	public int dieRoll; 
	// TODO need to be able call methods in Board class
	private Board board;
	private JPanel nextPlayerAndAccusation;
	private JPanel currentPlayerAndDieRoll;
	private JTextField currentName; 
	private JTextField currentDie; 
	private JTextField currentGuess;
	private JTextField currentResult;
	JFrame accusationWindow = new JFrame("Accusation");

	public ControlGUI()
	{

		// TODO need to call Board method for current dice roll value 
		dieRoll = 0; // FIXME
		board = Board.getInstance();
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.setWeaponsConfigFile("WeaponsConfig.txt");
		board.setPeopleConfigFile("PeopleConfig.txt");
		board.initialize();
		board.buildGamePlayers();
		currentName = new JTextField(board.whoIsTheCurrentPLayer().getPlayerName());
		currentDie = new JTextField(String.valueOf(board.currentDieRollValue()));
		currentGuess = new JTextField(board.whatIsTheCurrentGuess());
		currentResult = new JTextField(board.whatIsTheCurrentResult());

		//JTextField field = new JTextField(); 
		setLayout(new GridLayout(2,0));
		this.currentPlayerAndDieRoll = createNamePanel();
		JPanel panel1 = createGuessPanel(); 
		JPanel panel2 = createDiePanel(); 
		JPanel panel3 = createGuessResultPanel(); 

		this.nextPlayerAndAccusation = createButtonPanel(); 

		add(this.currentPlayerAndDieRoll);
		add(nextPlayerAndAccusation);
		add(panel2); 
		add(panel1);
		add(panel3);

	}

	public JPanel createNamePanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		//JLabel nameLabel = new JLabel("Name");
		// TODO call a Board method to get the current player
		name = new JTextField(board.whoIsTheCurrentPLayer().getPlayerName()); // FIXME
		currentName.setEditable(false);
		//panel.add(nameLabel);
		//panel.add(name);
		panel.add(currentName);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Whose turn?"));
		return panel;
	}

	private JPanel createGuessPanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		JLabel nameLabel = new JLabel("Guess");   
		name = new JTextField(20);
		name.setEditable(false);
		panel.add(nameLabel);
		currentGuess.setEnabled(false);
		//panel.add(name);
		panel.add(currentGuess);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		return panel;
	}

	private JPanel createDiePanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		JLabel nameLabel = new JLabel("Roll"); 
		String die = String.valueOf(dieRoll);
		name = new JTextField(die);
		this.currentDie.setEditable(false);
		name.setEditable(false);
		panel.add(nameLabel);
		//panel.add(name);
		panel.add(this.currentDie);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Die"));
		return panel;
	}

	private JPanel createGuessResultPanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		JLabel nameLabel = new JLabel("Response");
		name = new JTextField(20);
		name.setEditable(false);
		panel.add(nameLabel);
		//panel.add(name);
		currentResult.setEnabled(false);
		panel.add(currentResult);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		return panel;
	}
	// NOTE: When button is pressed , it calls the appropriate methods
	// in the Board class for processing. 
	private JPanel createButtonPanel() {
		JButton nextPlayer = new JButton("Next player");
		// NOTE: nextPlayer needs to be a listener
		nextPlayer.addActionListener(new NextPlayerButtonListener());
		// TODO accusation need to addActionListener
		JButton accusation = new JButton("Make an accusation");
		accusation.addActionListener(new MakeAccusationButtonListener());
		JPanel panel = new JPanel();
		panel.add(nextPlayer);
		panel.add(accusation); 
		return panel;
	}

	// NOTE: need a method to update the createDiePanel and createNamePanel

	public void refreshDieAndNamePanel()
	{
		this.currentName.setText(board.whoIsTheCurrentPLayer().getPlayerName());  
		this.currentName.setEditable(false);
		this.currentDie.setText(String.valueOf(board.currentDieRollValue()));
		//this.currentPlayerAndDieRoll.repaint();
	}
	
	public void refreshGuessResultPanels()
	{
		this.currentGuess.setText(board.whatIsTheCurrentGuess());
		//this.currentGuess.setEditable(false);
		this.currentResult.setText(board.whatIsTheCurrentResult());
		//this.currentResult.setEditable(false);
	}

	// NOTE: class implements ActionListener which is required for the
	// nextPlayer button
	private class NextPlayerButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			if (board.doneWithHuman) // && board.inWindow == false) 
			{
				
				// TODO call appropriate methods in the Board Class for processing 
				board.nextPlayerButtonMethod();
				// TODO need to refresh the createDiePanel and createNamePanel
				refreshDieAndNamePanel();
				board.GamePlay();
				refreshGuessResultPanels();
			}
		}
	}

	private class MakeAccusationButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			accusationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			JPanel accusationPanel = new JPanel();
			Accusation accusationClass = new Accusation();
			accusationPanel = accusationClass;
			accusationPanel.setLayout(new BoxLayout(accusationPanel, BoxLayout.Y_AXIS));
			accusationPanel.setOpaque(true);
			
			accusationWindow.getContentPane().add(BorderLayout.CENTER, accusationPanel);
			accusationWindow.pack();
			accusationWindow.setLocationByPlatform(true);
			accusationWindow.setVisible(true);
			accusationWindow.setResizable(true);
		}
	}

	// TODO function made for TESTING purposes
	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Control GUI");
		frame.setSize(250, 150);	
		// Create the JPanel and add it to the JFrame
		ControlGUI gui = new ControlGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}


}
