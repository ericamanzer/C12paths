package clueGame;
import java.util.*;
import java.io.*;

public class ComputerPlayer extends Player {
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

	public void createSuggestion(TBD) {

	}
	 */




} 
