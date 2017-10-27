// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.Moveable;
import java.util.Arrays;

public class Kangaroo implements Moveable {

	char val;
	int loc; 
	static Drawable [] board = new Drawable[GameEngine.BOARD_SIZE];

	// assigns values 
	public Kangaroo (char value )
	{
		val = value;
		loc = 0;
	}

	public void draw() {
		System.out.print(val);
	}
	
	// moves the place one location forward
	public void move(Drawable[] pieces, int playerLocation) {
		// playerLocation = the actual user's player location
		int oldLoc = 0;
		int newLoc = 0;
		for (int i = 0; i < GameEngine.BOARD_SIZE - 1; i++)
		{
			//Drawable [] temp = new Drawable[1];
			//Drawable [] temp2 = new Drawable[1];
			
			//temp[0] = pieces[i];
			//temp2[0] = new Kangaroo('K');
			
			Kangaroo bar = new Kangaroo('K');
			
			if (pieces[i].equals(bar))
			{
				System.out.println( "Found Kangaroo");
				oldLoc = i;
				// updating location of the Kangaroo piece
				// testing to see if anything is changing
				newLoc = oldLoc + 2;
				Doormat foo = new Doormat(' ');
 				if (pieces[newLoc] == foo) {
 					pieces[newLoc] = new Kangaroo('K');
 					pieces[oldLoc] = new Doormat(' ');
 				}
 				else 
 				{
 					newLoc = oldLoc + 1;
 					pieces[newLoc] = new Kangaroo('K');
 					pieces[oldLoc] = new Doormat(' ');
 				}
			}
		}
		
		// updating the board that will be returned. 
		for ( int h = 0; h < 21; h++)
		{
			board[h] = pieces[h];
		}
		
	}
	public Drawable[] getPieces()
	{
		return board;
	}

}
