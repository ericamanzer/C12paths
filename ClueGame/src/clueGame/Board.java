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
	static private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private String boardConfigFile;
	private String roomConfigFile;
	// Functions:
	//NOTE: Singleton pattern 
	private static Board theInstance = new Board();
	private Board() {}
	public static Board getInstance() 
	{
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
		targets = new HashSet<BoardCell>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		visited = new HashSet<BoardCell>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		
		//NOTE: used to load configuration files
		loadRoomConfig();
		loadBoardConfig();
		
		//find adjacencies
		calcAdjacencies();
		
	}

	public void calcAdjacencies() 
	{ 
		
		// calculating the adjacency
		for (int i = 0; i < numRows; i++)  // i = x
		{
			for (int j = 0; j < numColumns; j++)  // j = y
			{
				Set<BoardCell> adj = new HashSet<BoardCell>();
				if ( i - 1 >= 0)
				{
					//checking if it is a walkway possibility
					if (board[i - 1][j].isWalkway()){
						switch (board[i][j].getDoorDirection()){
						case UP:
							adj.add(board[i - 1][j]);
							break;
						case NONE:
							adj.add(board[i - 1][j]);
							break;
						default:
							break;
						}
					}
					
					//checking if it is a door with the correct direction
					if (board[i - 1][j].isDoorway() && board[i - 1][j].getDoorDirection() == DoorDirection.DOWN)
					{
						adj.add(board[i - 1][j]);
					}
					
					//checking if it is a room with DoorDirection.NONE
					if (board[i][j].isRoom() && board[i][j].getDoorDirection() == DoorDirection.NONE)
					{
						if ( adj.contains(board[i - 1][j]))
						{
							adj.remove(board[i - 1][j]);
						}
					}
				}
				if (i + 1 < numRows)
				{
					//checking if it is a walkway possibility
					if (board[i + 1][j].isWalkway()){
						switch (board[i][j].getDoorDirection()){
						case DOWN:
							adj.add(board[i + 1][j]);
							break;
						case NONE:
							adj.add(board[i + 1][j]);
							break;
						default:
							break;
						}
					}
					//checking if it is a door with the correct direction
					if (board[i + 1][j].isDoorway() && board[i + 1][j].getDoorDirection() == DoorDirection.UP)
					{
						adj.add(board[i + 1][j]);
					}
					
					//checking if it is a room with DoorDirection.NONE
					if (board[i][j].isRoom() && board[i][j].getDoorDirection() == DoorDirection.NONE)
					{
						if ( adj.contains(board[i + 1][j]))
						{
							adj.remove(board[i + 1][j]);
						}
					}
				}
				if (j - 1 >= 0)
				{
					//checking if it is a walkway possibility
					if (board[i][j - 1].isWalkway()){
						if (board[i][j] == board[11][6]) System.out.println("HERE j - 1");
						switch (board[i][j].getDoorDirection()){
						case LEFT:
							adj.add(board[i][j - 1]);
							break;
						case NONE:
							adj.add(board[i][j - 1]);
							break;
						default:
							break;
						}
					}
					//checking if it is a door with the correct direction
					if (board[i][j - 1].isDoorway() && board[i][j - 1].getDoorDirection() == DoorDirection.RIGHT)
					{
						adj.add(board[i][j - 1]);
					}
					
					//checking if it is a room with DoorDirection.NONE
					if (board[i][j].isRoom() && board[i][j].getDoorDirection() == DoorDirection.NONE)
					{
						if ( adj.contains(board[i][j - 1]))
						{
							adj.remove(board[i][j - 1]);
						}
					}
				}
				if (j + 1 < numColumns)
				{
					//checking if it is a walkway possibility
					if (board[i][j + 1].isWalkway()){
						switch (board[i][j].getDoorDirection()){
						case RIGHT:
							adj.add(board[i][j + 1]);
							break;
						case NONE:
							adj.add(board[i][j + 1]);
							break;
						default:
							break;
						}
					}
					//checking if it is a door with the correct direction
					if (board[i][j + 1].isDoorway() && board[i][j + 1].getDoorDirection() == DoorDirection.LEFT)
					{
						adj.add(board[i][j + 1]);
					}
					
					//checking if it is a room with DoorDirection.NONE
					if (board[i][j].isRoom() && board[i][j].getDoorDirection() == DoorDirection.NONE)
					{
						if ( adj.contains(board[i][j + 1]))
						{
							adj.remove(board[i][j + 1]);
						}
					}
				}
				
				
				adjMatrix.put(board[i][j], new HashSet<BoardCell>(adj));
				adj.clear();
			}
		}
	}

	public void calcTargets(int row, int col, int pathlength) 
	{ 
		// set visited list to empty
		visited.clear();
		// initially set targets to an empty list
		targets.clear();
		// add start location to the visited list
		visited.add(board[row][col]);
		/*
		 * Thinking that I would run test based on what the being startCell is.
		 * Example, if the being startCell is a door with step 1, there is only one place it can go because of DoorDirection.
		 * A switch would help diff. the findAllTargets functions. Afterwards, it would be called recursively. 
		 * findAllTargets(row, col, pathlength);
		 */
		if (board[row][col].isWalkway()) findAllTargetsWalkway(row, col, pathlength);
		if (board[row][col].isDoorway()) findAllTargetsDoorway(row, col, pathlength);
		if (board[row][col].isRoom()) findAllTargetsRoom(row, col, pathlength);
	}

	public void findAllTargetsWalkway(int row, int col, int pathLength)
	{
		if (board[row][col] == board[17][16]) System.out.println("HERE WALKWAY");
		// A for loop for adjCellMAIN
		if (visited.isEmpty()) 
		{
			targets.clear();
			visited.add(board[row][col]); 
		}
		// These sets are startcell's possible directions
		Set<BoardCell> adjCellUP = new HashSet<BoardCell>();
		Set<BoardCell> adjCellDOWN= new HashSet<BoardCell>();
		Set<BoardCell> adjCellRIGHT = new HashSet<BoardCell>();
		Set<BoardCell> adjCellLEFT = new HashSet<BoardCell>();
		
		// Instead of four different sets, have one. The elements can not repeat anyways.
		Set<BoardCell> adjCellMAIN = new HashSet<BoardCell>();
		
		// collecting the set from adjMatrix to make the adjCellUP set
		if ( row - (pathLength - 1) >= 0 && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row - (pathLength - 1)][col]))
			{
				visited.add(board[row - (pathLength - 1)][col]);
				adjCellUP = adjMatrix.get(board[row - (pathLength - 1)][col]);
				for (BoardCell add: adjCellUP)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
					if (add.getDoorDirection() == DoorDirection.DOWN) adjCellMAIN.add(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix." );
			}
		}

		// collecting the set from adjMatrix to make the adjCellDOWN set
		if ( row + (pathLength - 1) < numRows && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row + (pathLength - 1)][col]))
			{
				visited.add(board[row + (pathLength - 1)][col]);
				adjCellDOWN = adjMatrix.get(board[row + (pathLength - 1)][col]);
				for (BoardCell add: adjCellDOWN)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
					if (add.getDoorDirection() == DoorDirection.UP) adjCellMAIN.add(add);
				}
				
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix." );
			}
		}
		
		// collecting the set from adjMatrix to make the adjCellLEFT set
		if ( col - (pathLength - 1) >= 0 && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row][col - (pathLength - 1)]))
			{
				visited.add(board[row][col - (pathLength - 1)]);
				adjCellLEFT = adjMatrix.get(board[row][col - (pathLength - 1)]);
				for (BoardCell add: adjCellLEFT)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
					if (add.getDoorDirection() == DoorDirection.RIGHT) adjCellMAIN.add(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix." );
			}
		}
		
		// collecting the set from adjMatrix to make the adjCellRIGHT set
		if ( col + (pathLength - 1) < numColumns && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row][col + (pathLength - 1)]))
			{
				visited.add(board[row][col + (pathLength - 1)]);
				adjCellRIGHT = adjMatrix.get(board[row][col + (pathLength - 1)]);
				for (BoardCell add: adjCellRIGHT)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
					if (add.getDoorDirection() == DoorDirection.LEFT) adjCellMAIN.add(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix." );
			}
		}
		
		// A for loop for adjCellMAIN
		for (BoardCell temp: adjCellMAIN)
		{
			if ( !visited.contains(temp))
			{
				visited.add(temp);
				if (pathLength == 0)
				{
					return;
				}
				else
				{
					targets.add(temp);
					findAllTargetsWalkway(row, col, pathLength - 1); 
				}
			}
		}
		// testing {
		if ( board[row][col] == board[21][7] && pathLength == 4)
		{
			for (BoardCell check: targets)
			{
				System.out.println("Targets: [" + check.getCol() + "][" + check.getRow() + "]" );
			}
		}
	}
	
	public void findAllTargetsDoorway(int row, int col, int pathLength)
	{
		if (board[row][col] == board[21][7] && pathLength == 1) {
			return;
		}
		if (visited.isEmpty()) 
		{
			targets.clear();
			visited.add(board[row][col]); 
		}
		// These sets are startcell's possible directions
		Set<BoardCell> adjCellUP = new HashSet<BoardCell>();
		Set<BoardCell> adjCellDOWN= new HashSet<BoardCell>();
		Set<BoardCell> adjCellRIGHT = new HashSet<BoardCell>();
		Set<BoardCell> adjCellLEFT = new HashSet<BoardCell>();
		
		// Instead of four different sets, have one. The elements can not repeat anyways.
		Set<BoardCell> adjCellMAIN = new HashSet<BoardCell>();
		
		// collecting the set from adjMatrix to make the adjCellUP set
		if ( row - (pathLength - 1) >= 0 && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row - (pathLength - 1)][col]))
			{
				visited.add(board[row - (pathLength - 1)][col]);
				adjCellUP = adjMatrix.get(board[row - (pathLength - 1)][col]);
				for (BoardCell add: adjCellUP)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix");
			}
		}

		// collecting the set from adjMatrix to make the adjCellDOWN set
		if ( row + (pathLength - 1) < numRows && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row + (pathLength - 1)][col]))
			{
				visited.add(board[row + (pathLength - 1)][col]);
				adjCellDOWN = adjMatrix.get(board[row + (pathLength - 1)][col]);
				for (BoardCell add: adjCellDOWN)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix");
			}
		}
		
		// collecting the set from adjMatrix to make the adjCellLEFT set
		if ( col - (pathLength - 1) >= 0 && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row][col - (pathLength - 1)]))
			{
				visited.add(board[row][col - (pathLength - 1)]);
				adjCellLEFT = adjMatrix.get(board[row][col - (pathLength - 1)]);
				for (BoardCell add: adjCellLEFT)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix");
			}
		}
		
		// collecting the set from adjMatrix to make the adjCellRIGHT set
		if ( col + (pathLength - 1) < numColumns && pathLength != 0)
		{
			if (adjMatrix.containsKey(board[row][col + (pathLength - 1)]))
			{
				visited.add(board[row][col + (pathLength - 1)]);
				adjCellRIGHT = adjMatrix.get(board[row][col + (pathLength - 1)]);
				for (BoardCell add: adjCellRIGHT)
				{
					adjCellMAIN.add(add);
					if (adjCellMAIN.contains(add) && add.getDoorDirection() != DoorDirection.NONE) adjCellMAIN.remove(add);
					if (adjCellMAIN.contains(add) && (!add.isWalkway())) adjCellMAIN.remove(add);
				}
			}
			else
			{
				System.out.println("The key was not found in the adjMatrix");
			}
		}
		
		// A for loop for adjCellMAIN
		for (BoardCell temp: adjCellMAIN)
		{
			if ( !visited.contains(temp))
			{
				visited.add(temp);
				if (pathLength == 0)
				{
					return;
				}
				else
				{
					targets.add(temp);
					findAllTargetsDoorway(row, col, pathLength - 1); 
				}

			}

		}
	}
	
	public void findAllTargetsRoom(int row, int col, int pathLength)
	{ 
		if (board[row][col] == board[17][16]) System.out.println("HERE ROOM");
		boolean validCell = true; 
		if (visited.isEmpty()) 
		{
			targets.clear();
			visited.add(board[row][col]); 
		}
		Set<BoardCell> adjCell = new HashSet<BoardCell>();
		if (adjMatrix.containsKey(board[row][col]))
		{
			adjCell = adjMatrix.get(board[row][col]);
		}
		
		else
		{
			System.out.println("The key was not found in the adjMatrix");
		}
		
		for (BoardCell temp: adjCell)
		{
			if ( !visited.contains(temp) && validCell == true)
			{
				visited.add(temp);
				if (pathLength == 1)
				{
					targets.add(temp);
				}
				else
				{
					findAllTargetsRoom(row, col, pathLength - 1); 
				}
				visited.remove(temp); 

			}

		}
		visited.clear();
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
	public Set<BoardCell> getAdjList( int row, int col)
	{
		BoardCell cell = new BoardCell();
		cell = getCellAt(row, col);
		Set<BoardCell> found = new HashSet<BoardCell>();
		found = adjMatrix.get(cell);
		//System.out.println("size of found set: " + found.size());
		return found;
	}
	
	public Set<BoardCell> getTargets()
	{
		return targets;
	}


}
