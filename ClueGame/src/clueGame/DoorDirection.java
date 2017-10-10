package clueGame;

public enum DoorDirection {
	UP, DOWN, RIGHT, LEFT, NONE;
}


//FIXME May need. If not, erase. 
	// I was unsure how much work the instructions wanted on this class. Everything below this comment might need double checked 
	/*
	
	Direction direction; 
	BoardCell position; 
	
	int MAX_BOARD_SIZE = 50;
	
	public void updatePosition(Direction direction, BoardCell position) {
		
		int row = position.getRow(); 
		int col = position.getCol(); 
		
		switch (direction) { 
		case UP: 
			if (row > 0) { 
				position.setRow(row - 1);
			}
			break; 
		case DOWN: 
			if (row < MAX_BOARD_SIZE - 1) {
				position.setRow(row + 1);
			}
			break; 
		case RIGHT: 
			if (col < MAX_BOARD_SIZE - 1) {
				position.setCol(col + 1);
			}
			break; 
		case LEFT: 
			if (col > 0) {
				position.setCol(col - 1);
			}
			break; 
		}
	}
	*/