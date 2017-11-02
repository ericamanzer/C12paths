package clueGame;
import java.util.*;
import java.io.*;
import clueGame.BoardCell;
import java.awt.Color;
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
	// Set that would hold the computer player
	private Set<ComputerPlayer> computerPlayers;
	// Set that would hold the human player
	private Set<HumanPlayer> humanPlayer;
	private Set<String> weapons; 
	private Set<String> rooms; 
	private Set<Card> deck;
	private String boardConfigFile;
	private String roomConfigFile;
	private String peopleConfigFile;
	private String weaponsConfigFile;
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
	public void setWeaponsConfigFile(String weaponsFile)
	{
		weaponsConfigFile = weaponsFile;
	}
	public void setPeopleConfigFile (String peopleFile)
	{
		peopleConfigFile = peopleFile;
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
				
				String card = lineArray[2]; 
				
				
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
						if (dir == 'R' || dir == 'r') cell = new BoardCell(x, y, letter, DoorDirection.RIGHT);
						if (dir == 'L'|| dir == 'l') cell = new BoardCell(x, y, letter, DoorDirection.LEFT);
						if (dir == 'U' || dir == 'u') cell = new BoardCell(x, y, letter, DoorDirection.UP);
						if (dir == 'D' || dir == 'd') cell = new BoardCell(x, y, letter, DoorDirection.DOWN);
						if (dir == 'N' || dir == 'n') cell = new BoardCell(x, y, letter, DoorDirection.NONE);
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

	public void loadPeopleConfig()
	{
		//TODO load People config file
		File file = new File(peopleConfigFile);
		Scanner scan = null;
		try 
		{
			scan = new Scanner(file);
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] lineArray = line.split(", ");
				String playerName = lineArray[0];
				//lineArray[1] = Color
				//lineArray[2] = col
				//lineArray[3] = row
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

	public void loadWeaponConfig() { 
		//TODO load room config file
		File file = new File(weaponsConfigFile);
		Scanner scan = null;
		try 
		{
			scan = new Scanner(file);
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				weapons.add(line);
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


	public void initialize() {
		legend = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		visited = new HashSet<BoardCell>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();

		computerPlayers = new HashSet<ComputerPlayer>();
		humanPlayer = new HashSet<HumanPlayer>();
		deck = new HashSet<Card>();

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
						//if (board[i][j] == board[11][6]) System.out.println("HERE j - 1");
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

		find(row, col, pathlength);
	}

	public void find (int row, int col, int pathLength)
	{
		Set<BoardCell> adjCell = new HashSet<BoardCell>();
		if (adjMatrix.containsKey(board[row][col]))
		{
			adjCell = adjMatrix.get(board[row][col]);
			for (BoardCell test: adjCell)
			{

				if (test.isDoorway() && !visited.contains(test))
				{
					targets.add(test);
				}

				if (visited.contains(test))
				{
					continue; 
				}
				else 
				{
					visited.add(test);
				}
				if (pathLength == 1)
				{
					targets.add(test);
				}
				else
				{
					find(test.getCol(), test.getRow(), pathLength - 1);
				}
				visited.remove(test);

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
		return found;
	}

	public Set<BoardCell> getTargets()
	{
		return targets;
	}

	public void selectAnswer() { 

	}

	public Card handleSugestion(TBD) {

	}

	public boolean checkAccusation(Solution accusation) {

	}


}






