/*
 * Author: Demonna Wade and Erica Manzer 
 */


package clueGame;

public class BoardCell {
	// two member variables to represent the row and column
	public int row, col;
	
	// constructor (optional)
	public BoardCell() {
		row = 0;
		col = 0;
	} 
	
	public BoardCell(int c, int r) { 
		row = r; 
		col = c; 
	}
	
}
