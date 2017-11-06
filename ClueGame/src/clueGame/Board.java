package clueGame;
import java.util.*;
import java.io.*;
import java.lang.*;
import clueGame.BoardCell;
import java.awt.Color;
import java.lang.reflect.Field;

public class Board extends BoardCell {
	// Variables:
	public static final int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;
	private Solution answerKey;
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
	private Set<Card> key; 
	private Set<Card> deck;
	private Set<Card> roomPile;
	private Set<Card> peoplePile;
	private Set<Card> weaponsPile;
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
		int count = 0;
		File file = new File(roomConfigFile);
		Scanner scan = null;
		try 
		{
			
			scan = new Scanner(file);
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] lineArray = line.split(",");
				String letterString = lineArray[0];
				char letter = letterString.charAt(0);
				legend.put(letter, lineArray[1]);
				//System.out.println(lineArray[2]);
				//String card = lineArray[1]; 
				//rooms.add(card);
				
				// adding to the roomPile
				
				if (count > 1)
				{
					Card room = new Card(lineArray[1], CardType.ROOM);
					roomPile.add(room);
					//System.out.println("Adding to roomPile");
				}
				count ++;
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
			System.out.println(b.getMessage());
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
		int count = 0;
		try 
		{
			scan = new Scanner(file);
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] lineArray = line.split(",");
				if (count <= 0)
				{
					HumanPlayer human = new HumanPlayer(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
					humanPlayer.add(human);
				}
				else
				{
					ComputerPlayer computer = new ComputerPlayer(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
					computerPlayers.add(computer);
				}
				count ++;
				
				// add players to larger deck
				Card people = new Card (lineArray[0], CardType.PERSON);
				peoplePile.add(people);
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
				//weapons.add(line);
				
				// add the weapons to the deck
				Card weapon = new Card (line, CardType.WEAPON);
				weaponsPile.add(weapon);
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
		answerKey = new Solution();
		computerPlayers = new HashSet<ComputerPlayer>();
		humanPlayer = new HashSet<HumanPlayer>();
		deck = new HashSet<Card>();
		key = new HashSet<Card>();
		roomPile = new HashSet<Card>();
		peoplePile = new HashSet<Card>();
		weaponsPile = new HashSet<Card>();

		//NOTE: used to load configuration files
		loadRoomConfig();
		loadPeopleConfig();
		loadWeaponConfig();
		loadBoardConfig();
		//find adjacencies
		calcAdjacencies();
		
		//deal deck
		dealDeck();

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
	
	public void dealDeck() { 
		
		
		// Temporary ArrayLists to keep track of each card type and the total deck dynamically 
		ArrayList<Card> possibleCards = new ArrayList<Card>();
		ArrayList<Card> possiblePeople = new ArrayList<Card>();  
		ArrayList<Card> possibleWeapons = new ArrayList<Card>(); 
		ArrayList<Card> possibleRooms = new ArrayList<Card>();
		
		
		// Loads the deck and temporary Arrayists with every card read into program 
		for (Card temp: peoplePile) {
			deck.add(temp); 
			possiblePeople.add(temp); 
		}
		for (Card temp: weaponsPile) { 
			deck.add(temp); 
			possibleWeapons.add(temp); 
		}
		for(Card temp: roomPile) {
			deck.add(temp); 
			possibleRooms.add(temp);
		}
		
		
		Random rand = new Random(); 
		// Get random person for murderer 
		int r = rand.nextInt(possiblePeople.size()); 
		key.add(possiblePeople.get(r)); 
		deck.remove(possiblePeople.get(r)); 
		answerKey.setAnswerKeyPerson(possiblePeople.get(r).getCardname());
		// Get random weapon for murder weapon 
		r = rand.nextInt(possibleWeapons.size()); 
		key.add(possibleWeapons.get(r)); 
		deck.remove(possibleWeapons.get(r));
		answerKey.setAnswerKeyWeapon(possibleWeapons.get(r).getCardname());
		// Get random room for crime scene 
		key.add(possibleRooms.get(r)); 
		deck.remove(possibleRooms.get(r)); 
		answerKey.setAnswerKeyRoom(possiblePeople.get(r).getCardname());
		// Loads remaining deck values into the temp ArrayList 
		for (Card temp: deck) {
			possibleCards.add(temp);  
		}
		System.out.println(possibleCards.size());
		
		// Keeps track of the cards all players have 
		ArrayList<Player> player = new ArrayList<Player>();
		
		for(HumanPlayer person: humanPlayer) {
			for (int j = 0; j < 3; j++) { 
				r = rand.nextInt(possibleCards.size());
				person.addCard(possibleCards.get(r));
				deck.remove(possibleCards.get(r)); 
				possibleCards.remove(r); 	
			}
		}
		
		for(ComputerPlayer person: computerPlayers) {
			for (int j = 0; j < 3; j++) { 
				r = rand.nextInt(possibleCards.size());
				person.addCard(possibleCards.get(r));
				deck.remove(possibleCards.get(r)); 
				possibleCards.remove(r); 	
			}
		}
		
	

		
	}
	
	
	
	/*
	public void selectAnswer() { 

	}

	public Card handleSugestion(TBD) {

	}

	public boolean checkAccusation(Solution accusation) {

	}
	*/
	
	// Getter for computerPlayers 
	// @param no parameter 
	// @return computerPlayers a set of player that are not the player 1 
	public Set<ComputerPlayer> getComputerPlayers() {
		return computerPlayers;
	}

	// Getter for humanPlayers	
	// @param no parameter 
	// @return humanPlayer a set of size one that only contains player 1 
	public Set<HumanPlayer> getHumanPlayer() {
		return humanPlayer;
	}

	// Getter for weapons 
	// @param no parameter 
	// @return weapons a set of Strings that represent each of the weapons possible 
	public Set<String> getWeapons() {
		return weapons;
	}

	// Getter for rooms 
	// @param no parameter 
	// @return rooms a set of strings that represent each of the rooms possible 
	public Set<String> getRooms() {
		return rooms;
	}

	// Getter for deck
	// @param no parameter 
	// @return deck a set of cards that represent each of the people, weapons, and rooms 
	public Set<Card> getDeck() {
		return deck;
	}

	// Getter for key 
	// @param no parameter 
	// @return key a set of card, one of each type of card, that represent the murderer, the killing weapon, and the room where the murder took place 
	public Set<Card> getKey() {
		return key; 
	}
	
	// Getter for roomPile
	// @param no parameter
	// @return roomPile the set of room cards
	public Set<Card> getRoomPile()
	{
		return roomPile;
	}
	
	// Getter for weaponsPile
	// @param no parameter
	// @return weaponsPile the set of weapons card
	public Set<Card> getWeaponsPile()
	{
		return weaponsPile;
	}
	
	// Getter for peoplePile
	// @param no parameter
	// @return peoplePile the set of people cards
	public Set<Card> getPeoplePile()
	{
		return peoplePile;
	}

}






