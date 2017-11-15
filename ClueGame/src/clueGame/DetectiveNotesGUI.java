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

public class DetectiveNotesGUI extends JPanel {
	//private JTextField name; 

	public DetectiveNotesGUI() { 
		
		setLayout(new GridLayout(2,0));
		JPanel panel = people();
		JPanel panel1 = weapons(); 
		JPanel panel2 = rooms(); 
		 
		add(panel);
		add(panel1);
		add(panel2);
		
		panel = peopleGuess(); 
		add(panel);
		 
		
		panel1 = weaponsGuess(); 
		add(panel1);
		
		
		panel2 = roomsGuess(); 
		add(panel2);
	}

	private JPanel people() {
		 
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(1,2));
		
		String[] people = {"CompSci", "MechE", "ChemE", "Mining", "Geology", "Physics"};
		for (int i = 0; i < 6; i ++) { 
		JCheckBox person = new JCheckBox(people[i]); 
		panel.add(person); 
		} 
		
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return panel;
	}
	
	private JPanel weapons() {
		 
			JPanel panel = new JPanel();
			
			panel.setLayout(new GridLayout(1,2));
			
			String[] weapons = {"Keyboard", "MatLab", "Chemical", "Pickaxe", "Rock", "Exams"};
			for (int i = 0; i < 6; i ++) { 
			JCheckBox weapon = new JCheckBox(weapons[i]); 
			panel.add(weapon); 
			} 
			
			panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon"));
			return panel;
	}
	
	private JPanel rooms() {
		 
			JPanel panel = new JPanel();
			
			panel.setLayout(new GridLayout(1,2));
			
			String[] rooms = {"Marquez", "Hill Hall", "Guggenheim", "Brown", "Randall", "Alderson", "Coolbaugh", "Elm", "Weaver"};
			for (int i = 0; i < 9; i ++) { 
			JCheckBox room = new JCheckBox(rooms[i]); 
			panel.add(room); 
			} 
			
			panel.setBorder(new TitledBorder (new EtchedBorder(), "Room"));
			return panel;
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
		String[] rooms = {"Marquez", "Hill Hall", "Guggenheim", "Brown", "Randall", "Alderson", "Coolbaugh", "Elm", "Weaver"}; 
		JComboBox roomsList = new JComboBox(rooms); 
		JPanel panel = new JPanel();
		panel.add(roomsList);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		return panel;
	}

	
	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI Example");
		frame.setSize(250, 150);	
		// Create the JPanel and add it to the JFrame
		DetectiveNotesGUI gui = new DetectiveNotesGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}


}