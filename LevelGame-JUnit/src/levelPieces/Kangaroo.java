// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.Moveable;
import java.util.Arrays;

public class Kangaroo implements Moveable {

	private char val;
	private static int loc; 
	static Drawable [] board = new Drawable[GameEngine.BOARD_SIZE];

	// assigns values 
	public Kangaroo (char value, int location )
	{
		val = value;
		loc = location;
	}

	public void draw() {
		System.out.print(val);
	}
	
	// moves the place one location forward
	public void move(Drawable[] pieces, int playerLocation) {
		// playerLocation = the actual user's player location
		Doormat foo = new Doormat(' ');
		while ( loc < GameEngine.BOARD_SIZE)
		{
			if (loc == playerLocation && pieces[loc] == foo) 
			{
				loc += 4;
				
			}
			if (loc != playerLocation && pieces[loc] != foo) 
			{
				loc += 4;
				return;
			}
			else 
			{
				loc += 4;
			}
			if (loc == 20)
			{
				loc = 0;
			}
		}
		
		
		// updating the board that will be returned. 
		for ( int h = 0; h < 21; h++)
		{
			board[h] = pieces[h];
		}
		
	}
	public int getLocation()
	{
		return loc;
	}
	public Drawable[] getPieces()
	{
		return board;
	}

}
