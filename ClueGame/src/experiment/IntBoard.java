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
				boardCell = new BoardCell();
				boardCell.col = i;
				boardCell.row = j;
				grid[i][j] = boardCell;
			}
		}
	}
	public void calcAdjancies() {
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
		// calculating the adjacency
		// for loop going through all the cell in grid {
		for (int i = 0; i < 4; i++)  // i = x
		{
			
			for (int j = 0; j < 4; j++)  // j = y
			{
				Set<BoardCell> adj = new HashSet<BoardCell>();
				//boardCell = grid[i][j];
				//System.out.println(i + " " + j);
				//  look at each neighbor {
				if ( i - 1 >= 0)
				{
					//add it to the adjacency list
					if (!adj.contains(grid[i - 1][j])) adj.add(grid[i - 1][j]);
				}
				if (i + 1 < 4)
				{
					//add it to the adjacency list
					//if (!setAdjMtx.contains(grid[i+1][j])) setAdjMtx.add(grid[i+1][j]); System.out.println("right");
					//if(adj.add(grid[i+1][j])) System.out.println("What");
					adj.add(grid[i+1][j]);
					
				}
				if (j - 1 >= 0)
				{
					//add it to the adjacency list
					if (!adj.contains(grid[i][j - 1])) adj.add(grid[i][j - 1]);
				}
				if (j + 1 < 4)
				{
					//add it to the adjacency list
					//if (!setAdjMtx.contains(grid[i][j + 1])) setAdjMtx.add(grid[i][j + 1]); System.out.println("down");
					//if(adj.add(grid[i][j + 1])) System.out.println("Yo");
					adj.add(grid[i][j + 1]);
					//System.out.println("Yo");
					
				}
				//System.out.println(adj.);
				//for (BoardCell x : adj) {
				//	System.out.println(i + " "+j + " The set " + x.col + " " +x.row);
				//}
				adjMtx.put(grid[i][j], new HashSet<BoardCell>(adj));
				adj.clear();
			}
			
		}
		//System.out.println(adjMtx.size());
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
		int count = 0;
		boolean validCell = true; 
		
		if (visited.isEmpty()) 
		{
			targets.clear();
			visited.add(startCell); 
		}
		Set<BoardCell> adjCell = new HashSet<BoardCell>();
		adjCell = adjMtx.get(startCell);
		
		 
		int x = startCell.col;
		int y = startCell.row; 
		
		if (x - 1 >= 0 && x + 1 < 4 && y - 1 >= 0 && y + 1 < 4)
		{
			validCell = false; 
		}
		
		//Map.Entry<BoardCell, Set<BoardCell>> temp : adjMtx.entrySet()
		for (BoardCell temp: adjCell)
		{
			if ( !visited.contains(temp) && validCell == true)
			{
				visited.add(temp);
				if (pathLength == 1)
				{
					targets.add(temp);
					count++;
				}
				else
				{
					findAllTargets(temp, pathLength - 1); 
				}
				visited.remove(temp); 

			}
			 
		}
		visited.clear();
		System.out.println(" Added to targets " + count + " times");
	}
	// getters
	public Set<BoardCell> getTargets()
	{
		//return null; 
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell)
	{
		//return null; 
		 Set<BoardCell> ans = new HashSet<BoardCell>();
		 ans = adjMtx.get(cell);
		 return ans;

	}
	
	public BoardCell getCell(int y, int x) { 
		
		//return null; 
		return grid[y][x];
	}



}
