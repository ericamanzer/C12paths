package experiment;

public class BoardCell {
	// two member variables to represent the row and column
	public int row, col;
	
	// constructor (optional)
	public BoardCell() {
		row = 0;
		col = 0;
	} 
	
	public BoardCell(int col, int row) { 
		this.row = row; 
		this.col = col; 
	}
	
}
