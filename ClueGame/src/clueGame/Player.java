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
	
	// color conversion function. 
	// @param strColor the color of the player
	// @return color return the Java Object Color
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
	
	// Player default constructor
	//@param no parameter
	//@return returns nothing; this is a default constructor
	Player()
	{
		this.playerName = "";
		this.row = 0;
		this.myCards = new HashSet<Card>();
		this.seenCards = new HashSet<Card>();
		this.color = Color.white;
	}
	
	// Player parameterized constructor
	//@param name player name 
	//@param r location of the player via row
	//@param c location of the player via column
	//@param color the string read from the file that says the player's color
	Player( String name, int r, int c, String color)
	{
		this.playerName = name;
		this.row = r;
		this.column = c;
		this.color = convertColor(color);
	}
	/*
	public Card disproveSuggestion(Solution suggestion) {
		
	}
	*/
	
	
}
