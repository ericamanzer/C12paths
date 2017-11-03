package clueGame;

public class HumanPlayer extends Player {
	
	// Default constructor
	//@param no parameters
	//@return nothing return; default constructor
	HumanPlayer()
	{
		super();
	}
	
	// Parameterized constructor
	//@param name player's name
	//@param r player's location via row
	//@param c player's location via column
	//@param color player's color that is read as a string from a file
	HumanPlayer(String name, int r, int c, String color)
	{
		super(name, r, c, color);
	}
}
