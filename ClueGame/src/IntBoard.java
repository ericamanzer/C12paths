import java.util.*;
import experiment.BoardCell;

// For this exercise, you will write and test the adjacency and 
// target calculation algorithms for a simple 4x4 grid.

public class IntBoard {
	
	int startCell, pathLength; 
	// data  structure containing the board cells
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	// visited list
	private Set<BoardCell> visited;
	// targets list
	private Set<BoardCell> targets;
	// grid - 2D array 
	private BoardCell[][] grid;
	
	// default constructor
	public IntBoard() 
	{
		super();
		startCell = 0;
		pathLength = 0;
		// A good application of an adjacency list is a map
		// initializing a hashmap using a static initializer
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		grid = new BoardCell [4][4];
	}

	public void calcAdjancies() {

	}

	public void calcTargets(int startCell, int pathLength) { 

	}

}
