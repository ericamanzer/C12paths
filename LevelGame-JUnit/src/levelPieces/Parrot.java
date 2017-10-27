// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;

public class Parrot extends GamePiece {
	int loc;
	char val;
	
	// assigns values
	public Parrot (char symbol, int location)
	{
		super(symbol, location);
		val = symbol;
		loc = location; 
	}
	
	// This is making all of the interaction pieces based off their symbol 
	public InteractionResult interact(Drawable [] pieces, int playerLocation)
	{
		if ( loc == playerLocation && val == 'e')
		{
			return InteractionResult.HIT;
		}
		
		// This is supposed to help the player win the game
		else if ( loc == playerLocation && val == 'o')
		{
			LevelEngine.level++;
			return InteractionResult.ADVANCE;
		}
		else if (((loc - 1) == playerLocation && loc - 1 > 0 && val == 'T'))
		{
			return InteractionResult.GET_POINT;
		}
		else if ( loc == playerLocation && val == '*' )
		{
			return InteractionResult.KILL;
		}
		// This is supposed to help the player win the game
		else if ( ((loc + 2) == playerLocation && loc + 2 < 21 && val == 'w'))
		{
			LevelEngine.points++;
			return InteractionResult.GET_POINT;
		}
		 
		return InteractionResult.NONE;
		
	}
	
	@Override 
	public void draw() {
		System.out.print(val);
	}
	
}
