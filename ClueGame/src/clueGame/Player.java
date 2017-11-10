package clueGame;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.lang.reflect.Field;

public class Player {

	private String playerName; 
	private int currentRow, currentColumn, previousRow, previousColumn; 
	private Color color; 
	private Set<Card> myCards; 
	private Set<Card> seenCards;
	
	// handling the the previous and current BoardCell for pickLocation for ComputerPlayer 
	private BoardCell current;
	private BoardCell previous; 
	
	
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
	public Player()
	{
		this.playerName = "";
		this.currentRow = 0;
		this.myCards = new HashSet<Card>();
		this.seenCards = new HashSet<Card>();
		this.color = Color.white;
	}
	
	// Player parameterized constructor
	//@param name player name 
	//@param r location of the player via row
	//@param c location of the player via column
	//@param color the string read from the file that says the player's color
	public Player( String name, String color, int r, int c )
	{
		this.playerName = name;
		this.currentRow = r;
		this.currentColumn = c;
		this.color = convertColor(color);
		this.myCards = new HashSet<Card>();
		this.seenCards = new HashSet<Card>();
		
	}
	/*
	public Card disproveSuggestion(Solution suggestion) {
		
	}
	*/
	public int getMyCardSize()
	{
		return myCards.size();
	}
	
	public void addCard(Card card) {
		myCards.add(card);
	} 
	
	public void clearCards() { 
		myCards.clear(); 
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	// called in the test to save the testing location into BoardCell previous
	public void savingTestLocation( BoardCell current)
	{
		previous.setCol(current.getCol());
		previous.setRow(current.getRow());
		previous.setInitial(current.getInitial());
		
		// missing initial and DoorDirection 
		
	}
	
	public void updatePosition(int c, int r) { 
		previousColumn = currentColumn; 
		currentColumn = c; 
		previousRow = currentRow; 
		currentRow = r; 
	}
	
	public int getPreviousColumn() {
		return previousColumn; 
	}
	
	public int getPreviousRow() {
		return previousRow; 
	}
	
	public void addSeen(Card card) {
		seenCards.add(card); 
	}
	
	public void clearSeenCards ()
	{
		seenCards.clear();
	}
	
	public Set<Card> getSeenCards() {
		return seenCards; 
	}
	
	public Set<Card> getMyCards() {
		return myCards; 
	}
	public int getCurrentRow() {
		return currentRow; 
	}
	public int getCurrentColumn() {
		return currentColumn; 
	}
}
