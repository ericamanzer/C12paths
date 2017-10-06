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
	// BoardCell object
	static private BoardCell boardCell;

	// default constructor
	public IntBoard() 
	{
		super();
		startCell = 0;
		pathLength = 0;
		boardCell = new BoardCell();

		// A good application of an adjacency list is a map
		// initializing a HashMap using a static initializer
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		grid = new BoardCell [4][4];
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				boardCell.col = j;
				boardCell.row = i;
				grid[i][j] = boardCell;
			}
		}
	}
	public void calcAdjancies() {
		// calculating the adjacency
		// for loop going through all the cell in grid {
		for (int i = 0; i < 4; i++)  // i = x
		{
			for (int j = 0; j < 4; j++)  // j = y
			{
				boardCell = grid[i][j];
				Set<BoardCell> setAdjMtx = new HashSet<BoardCell>();
				//  look at each neighbor {
				if ( i - 1 >= 0)
				{
					//add it to the adjacency list
					setAdjMtx.add(boardCell);
				}
				if (i + 1 < 4)
				{
					//add it to the adjacency list
					setAdjMtx.add(boardCell);
				}
				if (j - 1 >= 0)
				{
					//add it to the adjacency list
					setAdjMtx.add(boardCell);
				}
				if (j + 1 < 4)
				{
					//add it to the adjacency list
					setAdjMtx.add(boardCell);
				}
				adjMtx.put(boardCell, setAdjMtx);
			}
		}
		//testing print out
		int count =  0;
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				System.out.print(adjMtx.get(grid[x][y]));
				count ++;
				if (count % 4 == 0)
				{
					System.out.println();
				}
			}
		}
	}

	
	public void calcTargets(int startCell, int pathLength) 
	{ 
		// set visited list to empty
		visited.clear();
		// initially set targets to an empty list
		targets.clear();
		// add start location to the visited list
		int x = startCell % 4;
		int y = startCell / 4;
		visited.add(grid[x][y]);
		// call recursive function (findAllTargets) 
		findAllTargets(startCell, pathLength);
	}

	public void findAllTargets(int startCell, int pathLength)
	{
		int x = startCell % 4;
		int y = startCell / 4;
		for (Map.Entry<BoardCell, Set<BoardCell>> temp : adjMtx.entrySet())
		{
			if ( visited.contains(temp.getKey()))
			{

			}
			else 
			{
				visited.add(temp.getKey());
				if (pathLength == 1)
				{
					targets.add(temp.getKey());
					return;
				}
				else
				{
					BoardCell tempCell = temp.getKey();
					// TODO
					int nextCell = 0;// new place
					//findAllTargets( /*int*/, pathLength - 1); 
				}
			}
		}
	}

	public static void main (String [] args)
	{
		IntBoard test = new IntBoard();
		test.calcAdjancies();
	}

}
