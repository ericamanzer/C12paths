/*
 * Author: Demonna Wade and Erica Manzer 
 */

/*
 * I made row, col, & initial private and then created getters and setters 
 * This created some problems in IntBoard since we had been accessing them directly. 
 * I went through and corrected by calling the getters and setters in IntBoard.
 * 
 * Mona {
 * 
 */


package clueGame;

public class BoardCell {
	// three member variables to represent the row, column, and initial 
	private int row, col;
	private char initial;
	private DoorDirection doorDir;

	// default constructor
	public BoardCell() {
		row = 0;
		col = 0;
		initial = 'W';
		doorDir = DoorDirection.NONE;
	} 
	// constructor with parameters
	public BoardCell(int c, int r, char initial, DoorDirection doorDir) { 
		row = r; 
		col = c; 
		this.initial = initial;
		this.doorDir = doorDir;
	}
	
	public boolean isWalkway() { 
		if (initial == 'W') {
			return true; 
		}
		return false;
	}
	public boolean isRoom() { 
		if (initial != 'X' && initial != 'W') { 
			return true; 
		}
		return false;
	}
	public boolean isDoorway() {
		if (doorDir != DoorDirection.NONE) {  
			return true; 
		}
		return false;
	}
	// Getters {
	public int getRow() {
		// correct way
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		// correct way
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public char getInitial() {
		// correct way
		return initial;
	}

	public void setInitial(char initial) {
		this.initial = initial;
	}
	public DoorDirection getDoorDirection()
	{
		// correct way
		return doorDir;
	}

}
