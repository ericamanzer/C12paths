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

	public CardDisplay()
	{

		
		
		setBorder(new TitledBorder (new EtchedBorder(), "My Cards"));
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
 

		setLayout(new GridLayout(2,0));
		JPanel panel = createNamePanel();

		panels.add(panel);

		add(panel);


	}

	private JPanel createNamePanel() {
		JTextField name; 
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		name = new JTextField(20);
		name.setEditable(false);
		panel.add(name);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return panel;
	}


 
	public static void main(String[] args) {
		// Create a JFrame with all the normal functionality
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Control GUI");
		frame.setSize(250, 150);	
		// Create the JPanel and add it to the JFrame
		CardDisplay gui = new CardDisplay();
		frame.add(gui, BorderLayout.CENTER);
		// Now let's view it
		frame.setVisible(true);
	}







}
