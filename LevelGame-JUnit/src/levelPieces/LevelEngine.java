// Authors: Demonna Wade & Erica Manzer

package levelPieces;

import java.util.ArrayList;
import java.util.Scanner;

import gameEngine.Moveable;
import gameEngine.Drawable;
import levelPieces.GamePiece;
import levelPieces.Doormat;


public class LevelEngine {

	public static int level;
	static int points;
	static boolean hit;
	static Drawable[] pieces = new Drawable[21];
	static ArrayList<Moveable> movingPieces = new ArrayList<Moveable>(2); 
	static ArrayList<GamePiece> interactingPieces = new ArrayList<GamePiece>(4);
	int playerLoc = 9;
	
	public void createLevel (int levelNum)
	{
		level = levelNum;
		points = 0;
		hit = false;
	}
	
	// makes the non-movable pieces in level 1 and level 2
	public Drawable[] getPieces()
	{

		
		if ( level == 1)
		{
			int location = 8;
			pieces[location] = new Doormat('d');
		}
		else if ( level == 2) 
		{
			int location = 13;
			pieces[location] = new Doormat('d');
		}
		
		return pieces;
	}

	// makes the movable pieces in level 1 and level 2
	public ArrayList<Moveable> getMovingPieces()
	{
	
		if (level == 1) 
		{
			int location = 5; 
			movingPieces.add(0, new Kangaroo('K', location));
			int loc = 2;
			movingPieces.add(1, new Giraffe('g', loc));
			
			pieces[location] = new Kangaroo('K', location);
			pieces[loc] = new Giraffe('g', location); 
		}
		else if (level == 2) 
		{ 
			int location = 4; 
			movingPieces.add(0, new Kangaroo('K', location));
			movingPieces.get(0).draw(); 
			int loc = 8;
			movingPieces.add(1, new Giraffe('g', location));
			
			pieces[location] = new Kangaroo('K', location); 
			pieces[loc] = new Giraffe('g', location); 
		}
		 
		return movingPieces;
	}

	// makes the interaction pieces in level 1 and level 2
	public ArrayList<GamePiece> getInteractingPieces()
	{
		if (level == 1) {
			int location = 19; 
			interactingPieces.add(0, new Parrot('e', location));
			pieces[location] = new Parrot('e', location);
			int loc = 5;
			interactingPieces.add(1, new Parrot('T', loc));
			pieces[loc] = new Parrot('T', loc);
			int l = 15; 
			interactingPieces.add(2, new Parrot('o', l));
			pieces[l] = new Parrot('o', l);
			int i = 12; 
			interactingPieces.add(3, new Parrot('*', i));
			pieces[i] = new Parrot('*', i);
			
		}
		else 
		{
			int location = 7;
			interactingPieces.add(0, new Parrot('e', location));
			pieces[location] = new Parrot('e', location);
			int l = 14; 
			interactingPieces.add(2, new Parrot('*', l));
			pieces[l] = new Parrot('*', l);
			int i = 1; 
			interactingPieces.add(3, new Parrot('*', i));
			pieces[i] = new Parrot('*', i);
			int j = 17; 
			interactingPieces.add(4, new Parrot('w', j));
			pieces[j] = new Parrot('w', j);
		}
		return interactingPieces;
	}
	
	//returns player location
	public int getPlayerStartLoc()
	{
		return playerLoc;
	}
}
