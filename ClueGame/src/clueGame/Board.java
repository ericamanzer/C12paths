package clueGame;
import java.util.*;
import java.io.*;
import clueGame.BoardCell;

public class Board extends BoardCell {
	// Variables:
	public static final int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;

	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private String boardConfigFile;
	private String roomConfigFile;
	// Functions:
	//NOTE: Singleton pattern 
	private static Board theInstance = new Board();
	private Board() {}
	public static Board getInstance() {
		return theInstance;
	}

	public void setConfigFiles(String boardFile, String roomFile)
	{
		boardConfigFile = boardFile;
		roomConfigFile = roomFile;
	}

	public void loadRoomConfig() { 
		//TODO load room config file
		File file = new File(roomConfigFile);
		Scanner scan = null;
		try 
		{
			scan = new Scanner(file);
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] lineArray = line.split(", ");
				System.out.println(lineArray.length);
				String letterString = lineArray[0];
				char letter = letterString.charAt(0);
				legend.put(letter, lineArray[1]);
			}

		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			System.out.println
			("Unable to open file " + roomConfigFile + ".");
		}
		catch (NullPointerException a)
		{
			BadConfigFormatException b = new BadConfigFormatException(a.getLocalizedMessage());
			b.getMessage();
		}
		finally
		{
			scan.close();
		}
	}

	public void loadBoardConfig() {
		//TODO load board config file
		int x = 0, y = 0;
		File file = new File (boardConfigFile);
		Scanner scan = null;
		try
		{
			scan = new Scanner(file);
			while (scan.hasNextLine())
			{
				String line = scan.nextLine(); 
				String[] array = line.split(",");
				numColumns = array.length;
				for (y = 0; y < numColumns; y++)
				{
					if (array[y].length() == 1)
					{
					char letter = array[y].charAt(0);
					BoardCell cell = new BoardCell(x, y, letter, DoorDirection.NONE);
					board[x][y] = cell;
					}
					if (array[y].length() == 2)
					{
						char letter = array[y].charAt(0);
						char dir = array[y].charAt(1);
						BoardCell cell = new BoardCell();
						if (dir == 'R') cell = new BoardCell(x, y, letter, DoorDirection.RIGHT);
						if (dir == 'L') cell = new BoardCell(x, y, letter, DoorDirection.LEFT);
						if (dir == 'U') cell = new BoardCell(x, y, letter, DoorDirection.UP);
						if (dir == 'D') cell = new BoardCell(x, y, letter, DoorDirection.DOWN);
						if (dir == 'N') cell = new BoardCell(x, y, letter, DoorDirection.NONE);
						board[x][y] = cell;
					}
				}
				x++;
			}
			numRows = x;
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			System.out.println
			("Unable to open/load " + boardConfigFile + " .");

		}
		catch (NullPointerException a) 
		{
			BadConfigFormatException b = new BadConfigFormatException(a.getMessage()); 
			b.getMessage();
		}
		finally
		{
			scan.close();
		}

	}

	public void initialize() {
		legend = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		//NOTE: used to load configuration files
		loadRoomConfig();
		loadBoardConfig();
	}

	public void calcAdjancies() 
	{ 
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		// calculating the adjacency
		for (int i = 0; i < 4; i++)  // i = x
		{
			for (int j = 0; j < 4; j++)  // j = y
			{
				Set<BoardCell> adj = new HashSet<BoardCell>();
				if ( i - 1 >= 0)
				{
					//add it to the adjacency list
					if (!adj.contains(board[i - 1][j])) adj.add(board[i - 1][j]);
				}
				if (i + 1 < 4)
				{
					//add it to the adjacency list
					adj.add(board[i+1][j]);

				}
				if (j - 1 >= 0)
				{
					//add it to the adjacency list
					if (!adj.contains(board[i][j - 1])) adj.add(board[i][j - 1]);
				}
				if (j + 1 < 4)
				{
					//add it to the adjacency list
					adj.add(board[i][j + 1]);

				}
				adjMatrix.put(board[i][j], new HashSet<BoardCell>(adj));
				adj.clear();
			}
		}
	}

	public void calcTargets(BoardCell cell, int pathlength) 
	{ 
		// set visited list to empty
		visited.clear();
		// initially set targets to an empty list
		targets.clear();
		// add start location to the visited list
		int x = cell.getCol();
		int y = cell.getRow();
		visited.add(board[x][y]);
		// call recursive function (findAllTargets) 
		findAllTargets(cell, pathlength);
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
		adjCell = adjMatrix.get(startCell);
		
		int x = startCell.getCol();
		int y = startCell.getRow(); 

		if (x - 1 >= 0 && x + 1 < 4 && y - 1 >= 0 && y + 1 < 4)
		{
			validCell = false; 
		}
		
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
		//System.out.println(" Added to targets " + count + " times");
	}

	//NOTE: Getters {
	public Map<Character, String> getLegend()
	{
		return legend;
	}
	public int getNumRows()
	{
		// correct way
		return numRows;
	}
	public int getNumColumns()
	{
		// correct way
		return numColumns;
	}
	public BoardCell getCellAt(int row, int col)
	{
		return board[row][col];
	}
	public BoardCell[][] getBoard()
	{
		return board;
	}


}
