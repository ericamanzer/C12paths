package clueGame;

import java.util.*; 


public class HumanPlayer extends Player {
	
	// Default constructor
	//@param no parameters
	//@return nothing return; default constructor
	public HumanPlayer()
	{
		super();
	}
	
	// Parameterized constructor
	//@param name player's name
	//@param r player's location via row
	//@param c player's location via column
	//@param color player's color that is read as a string from a file
	public HumanPlayer(String name, String color, int r, int c)
	{
		super(name, color, r, c);
	} //hello

}
