package clueGame;
import javax.swing.JFrame;   //need for creating JFrame
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class ClueGame extends JFrame {
	private final static int HEIGHT = 500;
	private final static int WIDTH = 1000;
	private static Board board;
	JPanel panel;
	
	public ClueGame()
	{
		board = Board.getInstance();
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.setWeaponsConfigFile("WeaponsConfig.txt");
		board.setPeopleConfigFile("PeopleConfig.txt");
		board.initialize();
		
		setTitle("Clue Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		// paintComponent is automatically be called 1 time
		panel = board;
		panel.setSize(500, 500);
		add(panel, BorderLayout.CENTER);
		JPanel control = new JPanel();
		ControlGUI guiControl = new ControlGUI();
		control = guiControl;
		add(control, BorderLayout.SOUTH);
		JPanel notes = new JPanel();
		notes.setSize(50, 10);
		DetectiveNotesGUI guiNotes = new DetectiveNotesGUI();
		notes = guiNotes;
		add(notes, BorderLayout.EAST);
	}
	
	public static void main(String[] args) {	
		JFrame frame = new ClueGame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		
	}

}
