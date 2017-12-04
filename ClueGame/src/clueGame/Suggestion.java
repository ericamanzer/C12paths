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

public class Suggestion extends JPanel {

	public Suggestion() { 
		//setBorder(new TitledBorder (new EtchedBorder(), "Suggestion"));
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


	private JPanel peopleGuess() {
		String[] people = {"CompSci", "MechE", "ChemE", "Mining", "Geology", "Physics"}; 
		JComboBox peopleList = new JComboBox(people); 
		JPanel panel = new JPanel();
		panel.add(peopleList);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People Guess"));
		return panel;
	}

	private JPanel weaponsGuess() { 
		String[] weapons = {"Keyboard", "MatLab", "Chemical", "Pickaxe", "Rock", "Exams"}; 
		JComboBox weaponsList = new JComboBox(weapons); 
		JPanel panel = new JPanel();
		panel.add(weaponsList);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		return panel; 
	}

	private JPanel roomsGuess() {

		JPanel panel = new JPanel();


		String name = "Room name"; // Get current room from board 
		JTextField textName = new JTextField(name); 
		textName.setEditable(false);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		panel.add(textName);

		return panel; 		
	}

	private JPanel buttonPanel() {
		//JButton accept = new JButton("Ok"); 
		//accept.addActionListener(new );    FIXME
		JButton cancel = new JButton("Cancel"); 
		//cancel.addActionListener();  FIXME 

		JPanel panel = new JPanel(); 
		//panel.add(accept);
		panel.add(cancel);
		return panel; 
	}

	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Make a suggestion");
		frame.setSize(250, 150);	
		// Create the JPanel and add it to the JFrame
		Suggestion gui = new Suggestion();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}


}
