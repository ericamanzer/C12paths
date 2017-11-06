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


	/*
	public BoardCell pickLocation(Set<BoardCell> targets) {

	}

	public void makeAccusation() {

	}

	public void createSuggestion(TBD) {

	}
	 */

	public BoardCell selectTarget() { 

		// call calcAdj function (targets set will be updated)  
		// call getter for previouslocation and previous row 
		// range loop to organize values into room or pathway 
		// if else statements to pick next location
		// if room = 0 

	}


} 
