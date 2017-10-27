// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import gameEngine.Drawable;
import gameEngine.Moveable;
import java.util.Random; 

public class Giraffe implements Moveable {

	private char val; 
	private static int loc; 

	// assigns values
	public Giraffe (char value, int location)
	{
		val = value;
		loc = location; 
	}

	public void draw() {
		System.out.print(val);
	}
	
	// assigns a new location moving two pieces and creates copies itself and removes copies. 
	public void move(Drawable[] pieces, int playerLocation) {
		if ( playerLocation != loc)
		{
			int i = loc * 2;
			if ( i >= 21)
			{
				i = 0; 
			}
			pieces[i] = new Giraffe ('g', i);
			pieces[loc] = null;
		}
		else 
		{
			pieces[playerLocation] = null; 
		}
	}
	
	public int getLocation()
	{
		return loc;
	}

}
