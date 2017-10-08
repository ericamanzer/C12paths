/*
 * Author: Demonna Wade and Erica Manzer 
 */

package experiment;
import java.util.*;

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
	private BoardCell boardCell;
	// Set used to add to adjMtx
	public Set<BoardCell> setAdjMtx;

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
		setAdjMtx = new HashSet<BoardCell>();
		grid = new BoardCell [4][4];
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				boardCell.col = j;
				boardCell.row = i;
				grid[j][i] = boardCell;
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
				//setAdjMtx.clear();
				boardCell = grid[i][j];
				System.out.println(i + " " + j);
				//  look at each neighbor {
				if ( i - 1 >= 0)
				{
					//add it to the adjacency list
					if (!setAdjMtx.contains(grid[i - 1][j])) setAdjMtx.add(grid[i - 1][j]);
				}
				if (i + 1 < 4)
				{
					//add it to the adjacency list
					//if (!setAdjMtx.contains(grid[i+1][j])) setAdjMtx.add(grid[i+1][j]); System.out.println("right");
					setAdjMtx.add(grid[i+1][j]);
					
				}
				if (j - 1 >= 0)
				{
					//add it to the adjacency list
					if (!setAdjMtx.contains(grid[i][j - 1])) setAdjMtx.add(grid[i][j - 1]);
				}
				if (j + 1 < 4)
				{
					//add it to the adjacency list
					//if (!setAdjMtx.contains(grid[i][j + 1])) setAdjMtx.add(grid[i][j + 1]); System.out.println("down");
					setAdjMtx.add(grid[i][j + 1]);
					
				}
				System.out.println(setAdjMtx.size());
				adjMtx.put(boardCell, setAdjMtx);
			}
			
		}
		System.out.println(adjMtx.size());
}

	

	public void calcTargets(BoardCell startCell, int pathLength) 
	{ 
		// set visited list to empty
		visited.clear();
		// initially set targets to an empty list
		targets.clear();
		// add start location to the visited list
		int x = startCell.col;
		int y = startCell.row;
		visited.add(grid[x][y]);
		// call recursive function (findAllTargets) 
		findAllTargets(startCell, pathLength);
	}

	public void findAllTargets(BoardCell startCell, int pathLength)
	{
		
		boolean validCell = true; 
		
		if (visited.isEmpty()) 
		{
			targets.clear(); 
		}
		
		visited.add(startCell); 
		 
		int x = startCell.col;
		int y = startCell.row; 
		
		if (x - 1 >= 0 && x + 1 < 4 && y - 1 >= 0 && y + 1 < 4)
		{
			validCell = false; 
		}
		
		for (Map.Entry<BoardCell, Set<BoardCell>> temp : adjMtx.entrySet())
		{
			if ( !visited.contains(temp.getKey()) && validCell == true)
			{
				visited.add(temp.getKey());
				if (pathLength == 1)
				{
					targets.add(temp.getKey());
				}
				else
				{
					findAllTargets(temp.getKey(), pathLength - 1); 
				}
				visited.remove(temp.getKey()); 

			}
			 
		}
		visited.clear();
	}
	// getters
	public Set<BoardCell> getTargets()
	{
		//return null; 
		return targets;
	}
	
	public Map<BoardCell, Set<BoardCell>> getAdjList()
	{
		//return null; 

		 return adjMtx;

	}
	
	public BoardCell getCell(int x, int y) {
		
		 
		
		//return null; 
		return boardCell; 
	}



}
