/*
 * Author: Demonna Wade and Erica Manzer 
 */


package experiment;

public class BoardCell {
	// two member variables to represent the row and column
	public int row, col;
	char initial; 
	
	// constructor (optional)
	public BoardCell() {
		row = 0;
		col = 0;
		initial = 'P';
	} 
	
	public BoardCell(int c, int r, char initial) { 
		row = r; 
		col = c; 
		this.initial = initial; 
	}
	
	public boolean isWalkway() { 
		boolean isW = false; 
		
		if (initial == 'P') {
			isW = true; 
		}
		
		return isW; 
	}
	
	public boolean isRoom() { 
		boolean isR = false; 
	
		if (initial != 'P' && initial != 'K') { //Doorway is considered part of the room 
			isR = true; 
		}
		
		return isR; 
	}
	
	public boolean isDoorway() {
		 boolean isD = false; 
		 
		 // initial '*' denotes doorway 
		 if (initial != '*') {
			 isD = true; 
		 }
		 
		 return isD; 
	}
	
}
