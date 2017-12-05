/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */

package clueGame;
import java.util.*;
import java.io.*;
import java.lang.*;
import clueGame.BoardCell;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder; 
import javax.swing.*;
import java.awt.Color;
import java.lang.reflect.Field;
import java.awt.*;    // library for Graphics 
import java.awt.event.*;
// library for JPanel
import javax.swing.*; 

// converted the Board class into a subclass of JPanel
public class Board extends JPanel implements MouseListener {
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
	ArrayList<Card> possibleCards = new ArrayList<Card>();
	public ArrayList<Card> possiblePeople = new ArrayList<Card>();  
	public ArrayList<Card> possibleWeapons = new ArrayList<Card>(); 
	public ArrayList<Card> possibleRooms = new ArrayList<Card>();
	ArrayList<Player> player = new ArrayList<Player>();
	ArrayList<Point> roomNames = new ArrayList<Point>();
	private JPanel panel;
	static JFrame suggestAccuseFrame; 
	JFrame myFrame; 

	boolean gameFinished = false; 

	// NOTE: Game logic variables 
	public boolean doneWithHuman = true;
	public boolean targetSelected = true;
	private boolean doneWithComputer = false;
	private Player currentPlayerInGame;
	public int currentPlayerInGameCount = -1;
	private BoardCell selectedBox;
	private ArrayList<Player> gamePlayers = new ArrayList<Player>();
	private int dieRollValue = -1;
	private boolean compReadyMakeAccusation = false;
	private boolean compSuggestionDisproved = true;
	private String currentGuess = "";
	private String currentResults = "no new clue";
	public boolean inWindow = false; 
	public boolean isFirstTurn = true; 


	// Functions:
	//NOTE: Singleton pattern 
	private static Board theInstance = new Board();
	private Board() 
	{
		Player emptyPlayer = new Player();
		this.currentPlayerInGame = emptyPlayer;
		this.panel = new JPanel();
		JLabel name = new JLabel("Clue Game Board");
		panel.add(name);
		addMouseListener(this); // adds the listener (aka PointListener) to the panel
		add(panel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(400, 400)); 

	}
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
				int x = Integer.parseInt(lineArray[3]);
				int y = Integer.parseInt(lineArray[4]);
				Point pixel = new Point (x * 32 + 50, y * 32 + 50);
				roomNames.add(pixel);
				char letter = letterString.charAt(0);
				legend.put(letter, lineArray[1]);
				//System.out.println(lineArray[2]);
				//String card = lineArray[1]; 
				rooms.add(lineArray[1]);

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
		// System.out.println("Set<String> room size: " + rooms.size());
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
				this.numColumns = array.length;
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
			this.numRows = x;
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
					Player player = new Player(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
					this.player.add(player);
				}
				else
				{
					ComputerPlayer computer = new ComputerPlayer(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
					computerPlayers.add(computer);
					Player player = new Player(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]));
					this.player.add(player);
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
		rooms = new HashSet<String>();

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


		possibleCards.clear(); 
		possiblePeople.clear();
		possibleWeapons.clear(); 
		possibleRooms.clear(); 		

		// Loads the deck and temporary ArrayLists with every card read into program 
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
		//System.out.println(possibleCards.size());


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
	public void selectAnswer() {}
	 */ 


	public boolean checkAccusation(Solution accusation) {

		String p, w, r;   
		p = accusation.getPerson(); 
		w = accusation.getWeapon(); 
		r = accusation.getRoom(); 

		// check person 
		if (!answerKey.getPerson().equals(p)) {
			return false; 
		}
		// check weapon 
		if (!answerKey.getWeapon().equals(w)) {
			return false; 
		}
		// check room 
		if (!answerKey.getRoom().equals(r)) {
			return false; 
		}

		gameFinished = true; 

		// If no differences exist then returns true 
		return true;
	}

	public Card handleSuggestion(ComputerPlayer computerPlayer) {

		int row = computerPlayer.getCurrentRow(); 
		int col = computerPlayer.getCurrentColumn();

		// createSuggestions saves the generated suggestion in ComputerPlayer's creadSoln (which is of type Solution)
		computerPlayer.createSuggestion(board[col][row], possiblePeople, possibleWeapons, rooms, computerPlayer); 
		this.currentGuess = (computerPlayer.getPlayerName() + ": " + computerPlayer.getCreatedSoln().getPerson() + ", " + computerPlayer.getCreatedSoln().getRoom() + ", " + computerPlayer.getCreatedSoln().getWeapon()) ;
		ArrayList<Card> foundCards = new ArrayList<Card>(); 

		for(ComputerPlayer tempPlayer: computerPlayers) {
			if (tempPlayer == computerPlayer) {
				continue;  
			}
			else { 
				// if a card is found by another player, the card is added to the ArrayList of cards
				Card temp = tempPlayer.disproveSuggestion(computerPlayer.createdSoln); 
				if ( temp == null) {}
				else { foundCards.add(temp); }
				
			}
		}

		// selecting a random number for selecting a found Card
		Random rand = new Random(); 
		int location = rand.nextInt(foundCards.size()); 

		if (foundCards.size() == 0) { /* if the size of FoundCards = 0, that means not cards were found to disprove the suggestion */
			// store the suggestion that was found to be the next accusation. 
			computerPlayer.setAccusation(computerPlayer.getCreatedSoln());
			this.compSuggestionDisproved = false;
			this.currentResults = "no new clue";
			return null;
		}
		else { 
			computerPlayer.addSeen(foundCards.get(location));
			this.compSuggestionDisproved = true;
			//System.out.println("Found other cards that disprove the suggestion. ArrayList size: " + foundCards.size() );
			if (foundCards.get(location) != null)
			{
				this.currentResults = foundCards.get(location).getCardname();
				return foundCards.get(location); 
			}
			else
			{
				// if null, need to choose another location to go to
				this.compSuggestionDisproved = true;
				this.currentResults = "no new clue";
				return null; 
			}
		}

	}

	public void paintComponent ( Graphics g)
	{
		// paintComponent method for drawing the board. It is drawn in an object-oriented manner
		// use an object-oriented approach that has each BoardCell object draw itself.
		super.paintComponent(g);
		// NOTE: call each BoardCell object to draw itself.
		// the draw method from BoardCell class will be called
		for ( int i = 0; i < 22; i++)
		{
			for ( int j = 0; j < 23; j++)
			{
				getCellAt(i, j).draw(g);
			}
		}
		//NOTE: drawing the computer players
		for ( ComputerPlayer comp: computerPlayers)
		{
			int x = comp.getCurrentRow();
			int y = comp.getCurrentColumn();
			Color color = comp.getColor();
			g.setColor(color);
			Point pixel = new Point( x * 32 + 50, y * 32 + 50);
			g.fillOval(pixel.x, pixel.y, 30, 30);
		}
		//NOTE: draw the human player
		for ( HumanPlayer human: humanPlayer)
		{
			int x = human.getCurrentRow();
			int y = human.getCurrentColumn();
			Color color2 = human.getColor();
			g.setColor(color2);
			Point pixel = new Point( x * 32 + 50, y * 32 + 50);
			g.fillOval(pixel.x, pixel.y, 30, 30);
		}
		//NOTE: drawing the rooms 
		ArrayList<String> names = new ArrayList<String>();
		names.add("Paths");
		names.add("Kafadar");
		names.add("Marquez");
		names.add("Hill Hall");
		names.add("Guggenheim");
		names.add("Brown");
		names.add("Randall");
		names.add("Alderson");
		names.add("Coolbaugh");
		names.add("Elm");
		names.add("Weaver");

		for ( int i = 0; i < names.size(); i++)
		{
			if ( i == 0) continue;
			Point pixel = new Point();
			pixel = roomNames.get(i);
			String room = names.get(i);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Calibri", Font.PLAIN, 18));
			g.drawString(room, pixel.x, pixel.y);
		}
		// NOTE: drawing the targets found on the board. There are highlighted in CYAN
		if ( this.currentPlayerInGame.getPlayerName().equals("CompSci") && targets.size() > 0)
		{
			for ( BoardCell cell: targets)
			{
				cell.drawTargets(g);
			}
		}
		// NOTE: when the human player is done with selecting a location, repaint the targeted cells 
		// back to path color
		if (this.currentPlayerInGameCount != 0)
		{
			for ( BoardCell cell: targets)
			{
				cell.reDrawTargets(g);
			}
		}

		if (this.currentPlayerInGame.getPlayerName().equals("CompSci"))
		{
			for ( HumanPlayer human: humanPlayer)
			{
				int x = human.getCurrentRow();
				int y = human.getCurrentColumn();
				Color color2 = human.getColor();
				g.setColor(color2);
				Point pixel = new Point( x * 32 + 50, y * 32 + 50);
				g.fillOval(pixel.x, pixel.y, 30, 30);
			}
		}

		if (( 	this.currentPlayerInGame.getPlayerName().equals("MechE")  	|| 
				this.currentPlayerInGame.getPlayerName().equals("ChemE") 	|| 
				this.currentPlayerInGame.getPlayerName().equals("Mining")	|| 
				this.currentPlayerInGame.getPlayerName().equals("Geology")	||
				this.currentPlayerInGame.getPlayerName().equals("Physics") ) && this.doneWithComputer)
		{
			for ( ComputerPlayer comp: computerPlayers)
			{
				int x = comp.getCurrentRow();
				int y = comp.getCurrentColumn();
				Color color = comp.getColor();
				g.setColor(color);
				Point pixel = new Point( x * 32 + 50, y * 32 + 50);
				g.fillOval(pixel.x, pixel.y, 30, 30);
			}
		}

	}

	public int rollDie() { 
		// NOTE: random dice roll used to the pathLength in calcTargets
		Random rand = new Random(); 
		int dieRoll = rand.nextInt(6) + 1; 
		return dieRoll; 
	}
	// TODO: check to make sure if working as desired
	public boolean updateComputerPosition(int col, int row, int pathlength, Player player) { 
		
		ArrayList<BoardCell> possibleTargets = new ArrayList<BoardCell>(); 
		// NOTE: calcTargets with refresh and populate the targets HashSet
		calcTargets(col, row, pathlength); 
		//System.out.println("Targets found for the computer: " + targets.size()); // TESTING
		for (BoardCell temp: targets) {
			// NOTE: populating the temp arrayList for "dumb" AI
			possibleTargets.add(temp); 
		}
		// NOTE: getting a random location for "dumb" AI
		Random rand = new Random(); 
		int location = rand.nextInt(possibleTargets.size()); 
		int c = possibleTargets.get(location).getCol(); 
		int r = possibleTargets.get(location).getRow(); 
		// NOTE: need to update the original set that holds the computer players
		for ( ComputerPlayer computer: computerPlayers)
		{
			// NOTE: ONLY change the player that is passed in
			if (player.getPlayerName().equals(computer.getPlayerName()))
			{
				// NOTE: update the "original" computer player with the player's changed location
				computer.updatePosition(c, r);
				this.doneWithComputer = true;
				revalidate();
				repaint(); 
			}
		}
		player.updatePosition(c, r);  // NOTE: probably unnecessary 
		
		return false; 
	}	
	// TODO: check to make sure if working as desired 
	public boolean updateHumanPosition(int col, int row, int pathlength, Player player) 
	{ 	

		//System.out.println("Col: " + col);
		///System.out.println("Row: " + row);
		//System.out.println("pathlength: " + pathlength);
		//System.out.println("Player information: " + player.getPlayerName());
		// NOTE: need to update the original set that holds the human player
		for (HumanPlayer human: humanPlayer)
		{
			if (human.getName() == player.getName())
			{
				// NOTE: update the "original" human player with the player's changed location
				human.updatePosition(col, row);
				this.doneWithHuman = true;	
				revalidate();
				repaint();

			}
		}

		return false; 

	}	

	public boolean containsClick( int mouseX, int mouseY, int targetX, int targetY)
	{
		Rectangle rect = new Rectangle( targetY, targetX, 30, 30);
		if ( rect.contains(new Point(mouseX, mouseY)))
		{
			return true;
		}
		return false;
	}

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
	public ArrayList<Player> getPlayersList()
	{
		System.out.println("test Player: " + player.get(0).getName());
		System.out.println( "Player arrayList size: " + player.size());
		return player;
	}
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

	// Getter for getAnswerKey() 
	// @param no parameter 
	// @return answerKey 
	public Solution getAnswerKey() {
		return answerKey; 
	}

	public void clearPossiblePeople() { 
		possiblePeople.clear();
	} 

	public void addPossiblePeople(Card card) { 
		possiblePeople.add(card); 
	}

	public void clearPossibleWeapons() { 
		possibleWeapons.clear();
	}

	public void addPossibleWeapons(Card card) { 
		possibleWeapons.add(card); 
	}

	public void clearPossibleRooms() { 
		rooms.clear();
	}

	public void addPossibleRooms(String room) { 
		rooms.add(room); 
	}

	public void setPossiblePeople(ArrayList<Card> possiblePeople) { 
		possiblePeople = this.possiblePeople; 
	}

	public void setPossibleWeapons(ArrayList<Card> possibleWeapons) { 
		possibleWeapons = this.possibleWeapons; 
	}

	public void setPossibleRooms(Set<String> rooms) { 
		rooms = this.rooms; 
	}

	public Board update()
	{
		return this.theInstance;
	}

	// TODO: this is the implementation of the MouseListener class that is required
	public void mousePressed (MouseEvent event) {}
	public void mouseClicked (MouseEvent event) 
	{
		if ( this.targetSelected == false && inWindow == false )
		{
			BoardCell whichBox = null;
			//selectedBox = null;
			// FIXME
			for ( int i = 0; i < 22; i++)
			{
				for ( int j = 0; j < 23; j++)
				{
					if (getCellAt(i, j).containsClick(event.getX(), event.getY()))
					{
						whichBox = getCellAt(i, j);
						repaint();
						break;
					}

				}
			}
			// NOTE: checking to see if the clicked BoardCell was part of the targets HashSet
			if (whichBox != null)
			{
				if ( targets.contains(whichBox)) 
				{
					selectedBox = whichBox;
					repaint();
					// TODO: would test if whichBox isDoorway()
					if (whichBox.isDoorway()) 
					{
						System.out.println("HELLO, WHY");
						/*
						inWindow = true; 
						
						myFrame = new JFrame("Suggestion");
						myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						try 
						{
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (Exception e) {
							e.printStackTrace();
						}

						JPanel myPanel = new JPanel();
						Suggestion suggest = new Suggestion(); 
						myPanel = suggest; 

						myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
						myPanel.setOpaque(true);

						JTextArea text = new JTextArea(15, 50);
						text.setEditable(false);
						text.setFont(Font.getFont(Font.SANS_SERIF));
						JPanel input = new JPanel(); 
						input.setLayout(new FlowLayout()); 
						myPanel.add(input);

						myFrame.getContentPane().add(BorderLayout.CENTER, myPanel); 
						myFrame.pack();
						myFrame.setLocationByPlatform(true);
						myFrame.setVisible(true);
						myFrame.setResizable(false);
						inWindow = false;
						*/
					}
					GamePlay();
					this.targetSelected = true; 
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "That is not a target", "Message", JOptionPane.INFORMATION_MESSAGE);
					repaint();
					GamePlay();
					return;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "That is not a target", "Message", JOptionPane.INFORMATION_MESSAGE);
				//System.out.println("Box selected was not a box");
				repaint();
			}
			revalidate();
			repaint();
		}
	}
	public void mouseReleased (MouseEvent event) {}
	public void mouseEntered (MouseEvent event) {}
	public void mouseExited (MouseEvent event) {}


	// NOTE: Game Play Logic Methods
	public void nextPlayerButtonMethod()
	{
		if (this.targetSelected)
		{
			// this method will be called when the "Next Player" button is clicked on
			if (this.currentPlayerInGameCount == -1) this.currentPlayerInGameCount = 0;
			else if (this.currentPlayerInGameCount == 5) this.currentPlayerInGameCount = 0;
			else { this.currentPlayerInGameCount ++; }
			// NOTE: updating the current player 
			Player emptyPlayer = new Player();
			if (this.currentPlayerInGameCount == -1) this.currentPlayerInGame = emptyPlayer;
			else { this.currentPlayerInGame = this.gamePlayers.get(this.currentPlayerInGameCount); }

			this.dieRollValue = rollDie(); 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must take your turn", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void buildGamePlayers()
	{
		for (HumanPlayer human: humanPlayer)
		{ this.gamePlayers.add(human); }
		for (ComputerPlayer computer: computerPlayers)
		{ this.gamePlayers.add(computer); }
		//for (Player computer: this.gamePlayers)
		//{ System.out.println(computer.getPlayerName()); }
	}

	public Player whoIsTheCurrentPLayer()
	{ return this.currentPlayerInGame; /* NOTE: Empty player was made to return when game first starts */  }

	public int currentDieRollValue()
	{ return this.dieRollValue; }

	public String whatIsTheCurrentGuess()
	{ return this.currentGuess; }

	public String whatIsTheCurrentResult()
	{ return this.currentResults; }

	public void GamePlay()
	{

		//System.out.println("Current Player: " + currentPlayerInGame.getPlayerName());
		if (this.currentPlayerInGame.getPlayerName().equals("CompSci"))
		{
			this.doneWithHuman = false;
			this.targetSelected = false; 
			int row = this.currentPlayerInGame.getCurrentRow();
			int col = this.currentPlayerInGame.getCurrentColumn();
			calcTargets(col, row, this.dieRollValue);
			repaint();

			this.updateHumanPosition(selectedBox.getCol(), selectedBox.getRow(), dieRollValue, this.currentPlayerInGame);  //ERROR
			repaint();
		}



		if (this.currentPlayerInGame.getPlayerName().equals("MechE")  		|| 
				this.currentPlayerInGame.getPlayerName().equals("ChemE") 	|| 
				this.currentPlayerInGame.getPlayerName().equals("Mining")	|| 
				this.currentPlayerInGame.getPlayerName().equals("Geology")	||
				this.currentPlayerInGame.getPlayerName().equals("Physics"))
		{
			this.doneWithComputer = false;
			int row = this.currentPlayerInGame.getCurrentRow(); 
			int col = this.currentPlayerInGame.getCurrentColumn();
			Card returnCardAnswer = new Card(); /* = generated Card created when handleSuggestion is called */

			
			repaint();
			this.updateComputerPosition(col, row, this.dieRollValue, this.currentPlayerInGame);
			// TODO
			if (this.compReadyMakeAccusation && !this.compSuggestionDisproved)
			{
				// make an accusation, the accusation will be the previous suggestion 
				for (ComputerPlayer computer : computerPlayers)
				{
					if (computer.getPlayerName().equals(this.currentPlayerInGame.getPlayerName())) /* find the computer that matches this.currentPlayerInGame */
					{
						// create one string that will be used in the JOptionPane
						Solution computerAnswer = new Solution();
						computerAnswer = computer.getAccusation();
						String compAnswer = computerAnswer.getPerson() + ", " + computerAnswer.getRoom() + "room, " + computerAnswer.getWeapon();
						// check computerAnser against the store answerKey
						if ( computerAnswer.getPerson().equals(answerKey.getPerson()) && 
							 computerAnswer.getRoom().equals(answerKey.getRoom()) && 
							 computerAnswer.getWeapon().equals(answerKey.getWeapon()) )
						{
							JOptionPane.showMessageDialog(null, "Computer Player: " + computer.getPlayerName() + " won the game. Answer was: "
							+ compAnswer, "Message", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Computer Player: " + computer.getPlayerName() + " was wrong. Solution given was: "
									+ compAnswer, "Message", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				
				
			}
			// suggestions can only be made when a player is in a room
			if (getCellAt(col, row).isDoorway())
			{
				System.out.println("Computer's Room location: " + getCellAt(row, col).getInitial());
				// In the ComputerPlayer class, creatSuggestion exists: 
				// 	- public void createSuggestion(BoardCell cell, ArrayList<Card> peopleArray, ArrayList<Card> weaponsArray, Set<String> rooms , ComputerPlayer player); 
				// need a ComputerPlayer object to call public Solution getCreatedSoln(); and createSuggestion( ... ); 
				// 	- Should manipulate the original set of ComputerPlayers!
				// All of the players except this.currentPlayerInGame need to call disproveSuggestion
				//  - Board class' handleSuggestion will run all of the above functionalities 
				for (ComputerPlayer computer : computerPlayers)
				{
					if (computer.getPlayerName().equals(this.currentPlayerInGame.getPlayerName())) /* find the computer that matches this.currentPlayerInGame */
					{
						// public Card handleSuggestion(ComputerPlayer computerPlayer);
						returnCardAnswer = handleSuggestion(computer);
						// TODO: if returnCardAnswer = null , then that means that the suggestion was not disproved. You need to update the ControlPanel with the results. 
						// making sure room, weapon, and person of computer.accusation is set to something before being able to make an accusation.
						this.compReadyMakeAccusation = computer.isAccusationReady();
					}
				}
			}
			else { this.currentResults = ""; this.currentGuess = ""; }

			repaint();
		}
	}
	
	public void cancelMyFrame() { 
		myFrame.setVisible(false);
		myFrame.dispose();
	}

	public void incorrectAccuation(Solution soln) { 

		String message = "Incorrect guess. " + soln.getPerson() + " " + soln.getWeapon() + " " 
				+ soln.getRoom() + " was not the answer. "; 

		JOptionPane.showMessageDialog(null, message);
	}

	public void correctAccuation(Solution soln) { 
		String message = "You win! " + soln.getPerson() + " " + soln.getWeapon() + " " + 
				soln.getRoom() + " was the correct answer!"; 

		JOptionPane.showMessageDialog(null, message);
	}

	
}

