// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import gameEngine.Drawable;

public class Doormat implements Drawable {
	char val;
	int loc;
	
	// Doormat does nothing. It just sits there
	public Doormat (char value)
	{
		val = value;
	}
	
	public void draw()
	{
		System.out.print(val);
	}
}
