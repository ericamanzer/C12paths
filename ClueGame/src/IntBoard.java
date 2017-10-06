import java.util.*;
import experiment.BoardCell;

public class IntBoard {
	
	int startCell, pathLength; 
	// data  structure containing the board cells
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	
	public IntBoard(int startCell, int pathLength) {
		super();
		this.startCell = startCell;
		this.pathLength = pathLength;
	}

	public void calcAdjancies() {

	}

	public void calcTargets(int startCell, int pathLength) { 

	}

}
