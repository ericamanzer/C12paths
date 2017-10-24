/*
 * Author: Demonna Wade and Erica Manzer 
 */

package experiment;
import java.util.*;

import clueGame.BoardCell_CR;

// For this exercise, you will write and test the adjacency and 
// target calculation algorithms for a simple 4x4 grid.

public class IntBoard {

	int startCell, pathLength; 
	// data  structure containing the board cells
	private Map<BoardCell_CR, Set<BoardCell_CR>> adjMtx;
	// visited list
	private Set<BoardCell_CR> visited;
	// targets list
	private Set<BoardCell_CR> targets;
	// grid - 2D array 
	private BoardCell_CR[][] grid;
	// BoardCell object
	private BoardCell_CR boardCell_CR;
	// Set used to add to adjMtx
	public Set<BoardCell_CR> setAdjMtx;

	// default constructor
	public IntBoard() 
	{
		super();
		startCell = 0;
		pathLength = 0;
		boardCell_CR = new BoardCell_CR();

		// A good application of an adjacency list is a map
		// initializing a HashMap using a static initializer
		adjMtx = new HashMap<BoardCell_CR, Set<BoardCell_CR>>();
		visited = new HashSet<BoardCell_CR>();
		targets = new HashSet<BoardCell_CR>();
		setAdjMtx = new HashSet<BoardCell_CR>();
		grid = new BoardCell_CR [4][4];
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				boardCell_CR = new BoardCell_CR();
				boardCell_CR.setCol(i);
				boardCell_CR.setRow(j); 
				grid[i][j] = boardCell_CR;
			}
		}
	}
	public void calcAdjancies() {
		adjMtx = new HashMap<BoardCell_CR, Set<BoardCell_CR>>();
		// calculating the adjacency
		// for loop going through all the cell in grid {
		for (int i = 0; i < 4; i++)  // i = x
		{
			
			for (int j = 0; j < 4; j++)  // j = y
			{
				Set<BoardCell_CR> adj = new HashSet<BoardCell_CR>();
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
				adjMtx.put(grid[i][j], new HashSet<BoardCell_CR>(adj));
				adj.clear();
			}
			
		}
		//System.out.println(adjMtx.size());
}

	

	public void calcTargets(BoardCell_CR startCell, int pathLength) 
	{ 
		// set visited list to empty
		visited.clear();
		// initially set targets to an empty list
		targets.clear();
		// add start location to the visited list
		int x = startCell.getCol();
		int y = startCell.getRow();
		visited.add(grid[x][y]);
		// call recursive function (findAllTargets) 
		findAllTargets(startCell, pathLength);
	}

	public void findAllTargets(BoardCell_CR startCell, int pathLength)
	{
		int count = 0;
		boolean validCell = true; 
		
		if (visited.isEmpty()) 
		{
			targets.clear();
			visited.add(startCell); 
		}
		Set<BoardCell_CR> adjCell = new HashSet<BoardCell_CR>();
		adjCell = adjMtx.get(startCell);
		
		 
		int x = startCell.getCol();
		int y = startCell.getRow(); 
		
		if (x - 1 >= 0 && x + 1 < 4 && y - 1 >= 0 && y + 1 < 4)
		{
			validCell = false; 
		}
		
		//Map.Entry<BoardCell, Set<BoardCell>> temp : adjMtx.entrySet()
		for (BoardCell_CR temp: adjCell)
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
	public Set<BoardCell_CR> getTargets()
	{
		//return null; 
		return targets;
	}
	
	public Set<BoardCell_CR> getAdjList(BoardCell_CR cell)
	{
		//return null; 
		 Set<BoardCell_CR> ans = new HashSet<BoardCell_CR>();
		 ans = adjMtx.get(cell);
		 return ans;

	}
	
	public BoardCell_CR getCell(int y, int x) { 
		
		//return null; 
		return grid[y][x];
	}



}
