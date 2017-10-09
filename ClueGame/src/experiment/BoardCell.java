/*
 * Author: Demonna Wade and Erica Manzer 
 */

/*
 * I made row, col, & initial private and then created getters and setters 
 * This created some problems in IntBoard since we had been accessing them directly. I went through and corrected by calling the getters and setters in IntBoard 
 */


package experiment;

public class BoardCell {
	// two member variables to represent the row and column
	private int row, col;
	private char initial; 
	
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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public char getInitial() {
		return initial;
	}

	public void setInitial(char initial) {
		this.initial = initial;
	}
	
}
