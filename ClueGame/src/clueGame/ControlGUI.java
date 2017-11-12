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
import java.util.*;

public class ControlGUI extends JPanel {
	//private JTextField name; 

	public ControlGUI()
	{
		// Create a layout with 2 rows
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		
		//JTextField field = new JTextField(); 
		
		setLayout(new GridLayout(2,0));
		JPanel panel = createNamePanel();
		JPanel panel1 = createGuessPanel(); 
		JPanel panel2 = createDiePanel(); 
		JPanel panel3 = createGuessResultPanel(); 
		
		panels.add(panel);
		panels.add(panel2);
		panels.add(panel1);  
		panels.add(panel3); 
		
		
		add(panel);
		panel = createButtonPanel(); 
		add(panel);
		 
		add(panel2); 
		add(panel1);
		add(panel3);
	}

	private JPanel createNamePanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		//JLabel nameLabel = new JLabel("Name");
		name = new JTextField(20);
		name.setEditable(false);
		//panel.add(nameLabel);
		panel.add(name);
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
		panel.add(name);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		return panel;
	}
	
	private JPanel createDiePanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
		JLabel nameLabel = new JLabel("Roll");
		name = new JTextField(20);
		name.setEditable(false);
		panel.add(nameLabel);
		panel.add(name);
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
		panel.add(name);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		return panel;
	}

	private JPanel createButtonPanel() {
		// no layout specified, so this is flow
		JButton nextPlayer = new JButton("Next player");
		JButton accusation = new JButton("Make an accusation");
		JPanel panel = new JPanel();
		panel.add(nextPlayer);
		panel.add(accusation); 
		return panel;
	}

	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI Example");
		frame.setSize(250, 150);	
		// Create the JPanel and add it to the JFrame
		ControlGUI gui = new ControlGUI();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}


}
