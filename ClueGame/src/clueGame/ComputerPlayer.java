package clueGame;
import java.util.*;
import java.io.*;

public class ComputerPlayer extends Player {
	
	Solution createdSoln = new Solution(); 
	
	//Parameterized Constructor
	//@param name computer's name
	//@param color color of the computer player
	//@param r computer's location via row
	//@param w computer's location via column
	public ComputerPlayer(String name, String color, int r, int c)
	{
		super(name, color, r, c);
	}

	// Default Constructor
	//@param no parameters
	//@return no return values; default constructors
	public ComputerPlayer()
	{
		super();
	}



	public BoardCell pickLocation(Set<BoardCell> targets) {

		ArrayList<BoardCell> availablePaths = new ArrayList<BoardCell>();
		ArrayList<BoardCell> availableDoorways = new ArrayList<BoardCell>();
		for (BoardCell cell : targets)
		{
			if (cell.isDoorway())
			{
				availableDoorways.add(cell);
			}
			if (cell.isWalkway())
			{
				availablePaths.add(cell);
			}
		}

		if (!availableDoorways.isEmpty())
		{
			int options = availableDoorways.size();
			Random rand = new Random(); 
			int selected = rand.nextInt(options); 
			//System.out.println( options + " doorways exist");

			return availableDoorways.get(selected);
		}
		else
		{
			int options = availablePaths.size();
			Random rand = new Random();
			int selected = rand.nextInt(options);
			//System.out.println("A pathway is available");
			return availablePaths.get(selected);
		}

	} 

	/*
	public void makeAccusation() {

	}
*/
	
	public void createSuggestion(TBD) {

	}

	public Card disproveSuggestion(Solution soln) {

		String p1, p2, w1, w2, r1, r2;  
		
		p1 = soln.getPerson();
		p2 = createdSoln.getPerson(); 
		w1 = soln.getWeapon(); 
		w2 = createdSoln.getWeapon(); 
		r1 = soln.getRoom(); 
		r2 = createdSoln.getRoom();
		
		ArrayList<Card> possibleReturns = new ArrayList<Card>(); 
		if(p1 == p2) {
			Card p = new Card(p1, CardType.PERSON); 
			possibleReturns.add(p); 
		}
		if (w1 == w2) { 
			Card w = new Card(w1, CardType.WEAPON); 
			possibleReturns.add(w); 
		}
		if (r1 == r2) {
			Card r = new Card(r1, CardType.ROOM); 
			possibleReturns.add(r); 
		}

		if (possibleReturns.size() == 0) {
			return null;
		}
		else {
			Random rand = new Random(); 
			int possition = rand.nextInt(possibleReturns.size() + 1);
			
			return possibleReturns.get(possition); 
		}
		 
	}


} 
