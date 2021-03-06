/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */

package clueGame;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.lang.reflect.Field;
import javax.swing.*;

public class Player extends JPanel {

	private String playerName; 
	private int currentRow, currentColumn, previousRow, previousColumn; 
	private Color colorMain; 
	private Set<Card> myCards; 
	private Set<Card> seenCards;
	private String colorString;
	
	// handling the the previous and current BoardCell for pickLocation for ComputerPlayer 
	private BoardCell current;
	private BoardCell previous; 
	
	
	// color conversion function. 
	// @param strColor the color of the player
	// @return color return the Java Object Color
	public Color convertColor (String strColor) 
	{
		this.colorString = strColor;
		Color color;
		try 
		{
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.toLowerCase());
			color = (Color)field.get(null);
		}
		catch (Exception e)
		{
			color = null;
			System.out.println("ERROR");
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
		this.colorMain = Color.white;
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
		this.colorMain = convertColor(color);
		this.myCards = new HashSet<Card>();
		this.seenCards = new HashSet<Card>();
		
	}
	public Card disproveSuggestion(Solution soln) {

		String p1, p2, w1, w2, r1, r2;  

		Set<Card> myCards = new HashSet<Card>(); 
		myCards = getMyCards(); 
		ArrayList<Card> cardsFound = new ArrayList<Card>();
		//System.out.println(myCards.size());
		for (Card found: myCards) {
			if (soln.getPerson().equals(found.getCardname())) {
				//System.out.println("1st");
				cardsFound.add(found); 
			}
			if (soln.getWeapon().equals(found.getCardname())) {
				//System.out.println("2nd");
				cardsFound.add(found); 
			}
			if (soln.getRoom().equals(found.getCardname())) {
				//System.out.println("3rd");
				cardsFound.add(found); 
			}
			 
		}

		if (cardsFound.size() == 0) {
			return null;
		}
		
		else if (cardsFound.size() == 1) { 
			return cardsFound.get(0); 
		}
		
		else {
			Random rand = new Random(); 
			int possition = rand.nextInt(cardsFound.size());

			return cardsFound.get(possition); 
		}

	}

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
	public Color getColor()
	{
		//System.out.println(colorMain);
		return this.colorMain;
	}
	
	public String getColorString()
	{
		return this.colorString;
	}
	
	public void updatePlayerName(String playerName)
	{
		this.playerName = playerName;
	}
}
