package clueGame;
import javax.swing.JButton;
import javax.swing.JFrame;   //need for creating JFrame
import javax.swing.JPanel;

//import clueGame.ControlGUI.ButtonListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClueGame extends JFrame {
	private final static int HEIGHT = 500;
	private final static int WIDTH = 1000;
	private static Board board;
	JPanel panel;
	private JPanel control;
	private boolean nextPlayer = false;
	public int playerCount = -1;
	private ArrayList<Player> playersList = new ArrayList<Player>();
	private Player currentPlayer;
	int dieRoll = 0;
	
	public ClueGame()
	{
		board = Board.getInstance();
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.setWeaponsConfigFile("WeaponsConfig.txt");
		board.setPeopleConfigFile("PeopleConfig.txt");
		board.initialize();
		playersList = board.getPlayersList();
		if ( playerCount != -1) this.currentPlayer = playersList.get(playerCount);
		else { this.currentPlayer = new Player("", "", 0, 0); }
		
		setTitle("Clue Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		// paintComponent is automatically be called 1 time
		panel = board;
		panel.setSize(400, 400);
		add(panel, BorderLayout.CENTER);
		
		control = new JPanel();
		ControlGUI guiControl = new ControlGUI(dieRoll, this.currentPlayer);
		control = guiControl;
		
		
		// TODO adding the actionListener buttons
		JButton nextPlayer = new JButton("Next player");
		// nextPlayer needs to be a listener
		nextPlayer.addActionListener(new ButtonListener());
		JButton accusation = new JButton("Make an accusation");
		control.add(nextPlayer);
		control.add(accusation);
		add(control, BorderLayout.SOUTH);
		
		JPanel notes = new JPanel();
		notes.setSize(50, 5);
		DetectiveNotesGUI guiNotes = new DetectiveNotesGUI();
		notes = guiNotes;
		add(notes, BorderLayout.EAST);
		CardDisplay guiCard = new CardDisplay(board); 
		JPanel cards = new JPanel(); 
		cards = guiCard; 
		cards.setSize(10,5);
		add(cards, BorderLayout.WEST);
		
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// will play the next player in the game
			setNextPlayer();
			// needs to check that a target is selected
			
			// needs to make sure the selected target is valid
			
			// testing 
			System.out.println( "Next player was clicked");
			
		}
	}
	
	public void setNextPlayer()
	{
		if (playerCount != 5) playerCount ++;
		else
		{
			playerCount = 0;
		}
		if (playerCount == -1) playerCount = 0;
		this.currentPlayer = playersList.get(playerCount);
		this.dieRoll = board.rollDie();
		
		playGame();
		
	}
	
	public void playGame()
	{
		// NOTE updating the ControlGUI
		control.removeAll();
		control = new ControlGUI(dieRoll, this.currentPlayer);
		JButton nextPlayer = new JButton("Next player");
		nextPlayer.addActionListener(new ButtonListener());
		JButton accusation = new JButton("Make an accusation");
		control.add(nextPlayer);
		control.add(accusation);
		add(control, BorderLayout.SOUTH);
		control.revalidate();

		
		// TODO get the player that is supposed to be playing 
		board.playPlayer(this.currentPlayer, this.dieRoll);
		Set<BoardCell> targetsFound = new HashSet<BoardCell>();
		targetsFound = board.update().getTargets();
		System.out.println("Size of targets: " + targetsFound.size());
	}
	
	
	public static void main(String[] args) {	
		ClueGame clueObject = new ClueGame();
		JFrame frame = clueObject;
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		Set<HumanPlayer>  humanPlayer = new HashSet<HumanPlayer>();
		humanPlayer = board.getHumanPlayer();
		String humanName = "";
		for (HumanPlayer human: humanPlayer)
		{
			humanName = human.getPlayerName();
		}
		// CompSci is the human player 
		String message = "You are " + humanName + ", press Next Player to begin play";
		JOptionPane.showMessageDialog(frame, message, "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);
		// need to play through the "game" until one of the "players" suggestions match the solution. 
	}

}
