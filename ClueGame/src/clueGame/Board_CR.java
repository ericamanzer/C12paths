package clueGame;
import java.util.*;
import java.io.*;
import clueGame.BoardCell_CR;

public class Board_CR extends BoardCell_CR {
	// Variables:
	public static final int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;

	private BoardCell_CR[][] board;
	private Map<Character, String> legend;
	static private Map<BoardCell_CR, Set<BoardCell_CR>> adjMatrix;
	private Set<BoardCell_CR> targets;
	private Set<BoardCell_CR> visited;
	private String boardConfigFile;
	private String roomConfigFile;
	// Functions:
	//NOTE: Singleton pattern 
	private static Board_CR theInstance = new Board_CR();
	private Board_CR() {}
	public static Board_CR getInstance() 
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
					BoardCell_CR cell = new BoardCell_CR(x, y, letter, DoorDirection.NONE);
					board[x][y] = cell;
					}
					if (array[y].length() == 2)
					{
						char letter = array[y].charAt(0);
						char dir = array[y].charAt(1);
						BoardCell_CR cell = new BoardCell_CR();
						if (dir == 'R') cell = new BoardCell_CR(x, y, letter, DoorDirection.RIGHT);
						if (dir == 'L') cell = new BoardCell_CR(x, y, letter, DoorDirection.LEFT);
						if (dir == 'U') cell = new BoardCell_CR(x, y, letter, DoorDirection.UP);
						if (dir == 'D') cell = new BoardCell_CR(x, y, letter, DoorDirection.DOWN);
						if (dir == 'N') cell = new BoardCell_CR(x, y, letter, DoorDirection.NONE);
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
		targets = new HashSet<BoardCell_CR>();
		board = new BoardCell_CR[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		visited = new HashSet<BoardCell_CR>();
		adjMatrix = new HashMap<BoardCell_CR, Set<BoardCell_CR>>();
		
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
				Set<BoardCell_CR> adj = new HashSet<BoardCell_CR>();
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
				
				
				adjMatrix.put(board[i][j], new HashSet<BoardCell_CR>(adj));
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
		find(row, col, pathlength);
	}

	public void find (int row, int col, int pathLength)
	{
		Set<BoardCell_CR> adjCell = new HashSet<BoardCell_CR>();
		if (adjMatrix.containsKey(board[row][col]))
		{
			adjCell = adjMatrix.get(board[row][col]);
			for (BoardCell_CR test: adjCell)
			{
				if (test.getCol() == 13 && test.getRow() == 4 ) System.out.println("SCREAM");
				if (visited.contains(test))
				{
					if (test.getCol() == 13 && test.getRow() == 4 ) System.out.println("SCREAM WHY");
				}
				else
				{
					if (test.getCol() == 13 && test.getRow() == 4 ) System.out.println("SCREAM WHAT");
					visited.add(test);
					if (pathLength == 1)
					{
						if (test.getCol() == 13 && test.getRow() == 4 ) System.out.println("SCREAM AGAIN");
						targets.add(test);
					}
					else
					{
						find(test.getCol(), test.getRow(), pathLength - 1);
					}
					visited.remove(test);
				}
			}
			//testing {
			if (board[row][col] == board[14][0] && pathLength == 6){
				for (BoardCell_CR see: targets)
				{
					System.out.println("Targets: [" + see.getCol() + "][" + see.getRow() + "]");
				}
			}
		}
		else 
		{
			System.out.println("This key does not exist in the adjMatrix");
		}
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
	public BoardCell_CR getCellAt(int row, int col)
	{
		return board[row][col];
	}
	public BoardCell_CR[][] getBoard()
	{
		return board;
	}
	public Set<BoardCell_CR> getAdjList( int row, int col)
	{
		BoardCell_CR cell = new BoardCell_CR();
		cell = getCellAt(row, col);
		Set<BoardCell_CR> found = new HashSet<BoardCell_CR>();
		found = adjMatrix.get(cell);
		//System.out.println("size of found set: " + found.size());
		return found;
	}
	public Set<BoardCell_CR> getTargets()
	{
		return targets;
	}


}
