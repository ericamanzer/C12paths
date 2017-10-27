// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;

// This is an abstract class because it contains an abstract 
// method AND an instance variable
public abstract class GamePiece implements Drawable {
	// Interfaces cannot have instance variables
	private char symbol;
	// Stores the piece location, which will be needed to interact
	// with the player. 
	private int location;

	public GamePiece(char symbol, int location) {
		super();
		this.symbol = symbol;
		this.location = location;
	}

	// Abstract classes can have concrete/defined methods
	public void draw() {
		System.out.print(symbol);
	}

	// Abstract classes should have at least one abstract method
	public abstract InteractionResult interact(Drawable [] pieces, int playerLocation);

	public int getLocation() {
		return location;
	}
	
	public void setLocation(int newLocation) {
		// Ensure the location remains on the board
		if (newLocation >= 0 && newLocation < GameEngine.BOARD_SIZE)
			location = newLocation;
	}
}
