/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.util.*;


public class CardDisplay extends JPanel{

	Set<Card> myCards = new HashSet<Card>();
	Set<Card> people = new HashSet<Card>();
	Set<Card> weapons = new HashSet<Card>();
	Set<Card> rooms = new HashSet<Card>();


	public CardDisplay(Board board)
	{

		Set<HumanPlayer> players = new HashSet<HumanPlayer>();  
		players =  board.getHumanPlayer();

		for(HumanPlayer temp: players) { 
			myCards = temp.getMyCards(); 
		}

		for (Card temp: myCards) { 
			if (temp.getCardType() == CardType.PERSON) { 
				people.add(temp); 
			}
			else if (temp.getCardType() == CardType.WEAPON) { 
				weapons.add(temp); 
			}
			else if (temp.getCardType() == CardType.ROOM) { 
				rooms.add(temp);
			}
		}


		setBorder(new TitledBorder (new EtchedBorder(), "My Cards"));

		ArrayList<JPanel> panels = new ArrayList<JPanel>();


		setLayout(new GridLayout(3,0));
		JPanel panel = personPanel();
		JPanel panel1 = weaponPanel();
		JPanel panel2 = roomPanel();

		panels.add(panel);
		panels.add(panel1);
		panels.add(panel2);


		add(panel);
		add(panel1);
		add(panel2);

	}

	private JPanel personPanel() {

		JPanel panel = new JPanel();
		
		for (Card temp: people) {
			String name = temp.getCardname();
			JTextField textName = new JTextField(name); 
			textName.setEditable(false);
			
			panel.add(textName);

		}



		panel.setLayout(new GridLayout(3,1));


		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return panel;
	}

	private JPanel weaponPanel() {

		JPanel panel = new JPanel();
		
		for (Card temp: weapons) {
			String name = temp.getCardname();
			JTextField textName = new JTextField(name); 
			textName.setEditable(false);
			
			panel.add(textName);

		}



		panel.setLayout(new GridLayout(3,1));


		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		return panel;
	}

	private JPanel roomPanel() {

		JPanel panel = new JPanel();
		
		for (Card temp: rooms) {
			String name = temp.getCardname();
			JTextField textName = new JTextField(name); 
			textName.setEditable(false);
			
			panel.add(textName);

		}



		panel.setLayout(new GridLayout(3,1));


		panel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		return panel;
	}


	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Control GUI");
		frame.setSize(250, 150);	
		// Create the JPanel and add it to the JFrame
		CardDisplay gui = new CardDisplay(null);
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}







}
