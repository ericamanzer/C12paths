package clueGame;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.lang.reflect.Field;

public class Player {

	private String playerName; 
	private int row, column; 
	private Color color; 
	private Set<Card> myCards; 
	private Set<Card> seenCards; 
	
	
	public Card disproveSuggestion(Solution suggestion) {
		
	}
	
	public Color convertColor (String strColor)
	{
		Color color;
		try 
		{
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());
			color = (Color)field.get(null);
		}
		catch (Exception e)
		{
			color = null;
		}
		return color;
	}

	
}
