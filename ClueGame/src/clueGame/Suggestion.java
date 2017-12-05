/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.ItemSelectable;

public class Suggestion extends JPanel {

	private Board board;
	JComboBox peopleList;  
	JComboBox weaponsList; 
	String roomName; 
	int state; 
	private String peopleAnswer;
	private String roomAnswer;
	private String weaponAnswer;
	String[] people = {"CompSci", "MechE", "ChemE", "Mining", "Geology", "Physics"};
	String[] weapons = {"Keyboard", "MatLab", "Chemical", "Pickaxe", "Rock", "Exams"};

	public Suggestion(String r) { 

		board = Board.getInstance();
		//board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		//board.setWeaponsConfigFile("WeaponsConfig.txt");
		//board.setPeopleConfigFile("PeopleConfig.txt");
		//board.initialize();
		//board.buildGamePlayers();


		roomName = r; // Get current room from board 

		peopleList = new JComboBox(people);
		weaponsList = new JComboBox(weapons);

		setBorder(new TitledBorder (new EtchedBorder(), "Suggestion"));
		setLayout(new GridLayout(4,1));
		JPanel panel = peopleGuess();
		JPanel panel1 = weaponsGuess();
		JPanel panel2 = roomsGuess(); 

		add(panel);
		add(panel1);		
		add(panel2);

		JPanel buttons = buttonPanel();
		add(buttons);
	}

	public void setCurrentRoom(String r) { 
		this.roomName = r; 
	}
	
	private JPanel peopleGuess() {

		JPanel panel = new JPanel();
		panel.add(peopleList);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People Guess"));
		return panel;
	}

	private JPanel weaponsGuess() { 

		JPanel panel = new JPanel();
		panel.add(weaponsList);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		return panel; 
	}

	private JPanel roomsGuess() {

		JPanel panel = new JPanel();

		JTextField textName = new JTextField(roomName); 
		textName.setEditable(false);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		panel.add(textName);

		return panel; 		
	}

	private JPanel buttonPanel() {

		JButton accept = new JButton("Submit"); 
		accept.addActionListener(new submitButtonListener() );
		JButton cancel = new JButton("Cancel"); 
		cancel.addActionListener(new cancelButtonListener());   

		JPanel panel = new JPanel(); 
		panel.add(accept);
		panel.add(cancel);
		return panel; 
	}

	private class cancelButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 
			System.out.println("Canceling suggestion class "); 
			board.closeMyFrame();

		}
	}

	private class submitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{ 


			int foundP = peopleList.getSelectedIndex();
			peopleAnswer = people[foundP]; 
			int foundW = weaponsList.getSelectedIndex();
			weaponAnswer = weapons[foundW];
			roomAnswer = roomName; 
			System.out.println("Answer Found: " + peopleAnswer + ", " + roomAnswer + " room, " + weaponAnswer);

 
			board.closeMyFrame();

		}
	}



}
