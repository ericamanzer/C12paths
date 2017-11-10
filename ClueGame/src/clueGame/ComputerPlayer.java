package clueGame;
import java.util.*;
import java.io.*;

public class ComputerPlayer extends Player {

	Solution createdSoln = new Solution(); 

	//Parameterized Constructor
	//@param name computer's name
	//@param color color of the computer player
	//@param r computer's location via row
	//@param w computer's location via column
	public ComputerPlayer(String name, String color, int r, int c)
	{
		super(name, color, r, c);
	}

	// Default Constructor
	//@param no parameters
	//@return no return values; default constructors
	public ComputerPlayer()
	{
		super();
	}



	public BoardCell pickLocation(Set<BoardCell> targets) {

		ArrayList<BoardCell> availablePaths = new ArrayList<BoardCell>();
		ArrayList<BoardCell> availableDoorways = new ArrayList<BoardCell>();
		for (BoardCell cell : targets)
		{
			if (cell.isDoorway())
			{
				availableDoorways.add(cell);
			}
			if (cell.isWalkway())
			{
				availablePaths.add(cell);
			}
		}

		if (!availableDoorways.isEmpty())
		{
			int options = availableDoorways.size();
			Random rand = new Random(); 
			int selected = rand.nextInt(options); 
			//System.out.println( options + " doorways exist");

			return availableDoorways.get(selected);
		}
		else
		{
			int options = availablePaths.size();
			Random rand = new Random();
			int selected = rand.nextInt(options);
			//System.out.println("A pathway is available");
			return availablePaths.get(selected);
		}

	} 

	/*
	public void makeAccusation() {

	}
	 */
	// Default Constructor
	//@param BoardCell cell, ArrayList<Card> people, ArrayList
	//@return no return values; default constructors
	public void createSuggestion(BoardCell cell, ArrayList<Card> peopleArray, ArrayList<Card> weaponsArray, Set<String> rooms, ComputerPlayer player) {
		//selecting the room suggestion based on the current location of the player
		ArrayList<Card> people = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();
		people = peopleArray;
		weapons = weaponsArray;
		char roomInitial = cell.getInitial();
		String room = "";
		for (String r : rooms)
		{
			if (roomInitial == r.charAt(0))
			{
				room = r;
			}
		}
		Card person = new Card("", CardType.PERSON);
		Card weapon = new Card("", CardType.PERSON);
		Set<Card> seen = new HashSet<Card>();
		seen = player.getSeenCards();
		int size = seen.size();
		System.out.println(size + " = seenCards size");
		boolean exit = true;
		int count = 0;
		while (exit)
		{
			Random rand = new Random();
			int select = rand.nextInt(people.size());
			person = people.get(select);
			select = rand.nextInt(weapons.size());
			weapon = weapons.get(select);
			System.out.println(count);
			// handle looking at seenCards and making sure to not 
			if ( seen.contains(person))
			{
				count = count + 1;
				people.remove(person);
			}
			if ( seen.contains(weapon))
			{
				weapons.remove(weapon);
			}
			else 
			{
				exit = false;
			}
			for (int i = 0; i < people.size(); i++)
			{
				System.out.println("People: " +  people.get(i).getCardname());
			}
			for (int i = 0; i < weapons.size(); i++)
			{
				System.out.println("Weapons " + weapons.get(i).getCardname());
			}
		}
		// make the suggestion using the Solution class
		createdSoln.setAnswerKeyPerson(person.getCardname());
		createdSoln.setAnswerKeyWeapon(weapon.getCardname());
		createdSoln.setAnswerKeyRoom(room);
	}

	public Card disproveSuggestion(Solution soln) {

		String p1, p2, w1, w2, r1, r2;  

		Set<Card> myCards = new HashSet<Card>(); 
		myCards = getMyCards(); 
		ArrayList<Card> cardsFound = new ArrayList<Card>();
		System.out.println(myCards.size());
		for (Card found: myCards) {
			if (soln.getPerson() == found.getCardname()) {
				System.out.println("1st");
				cardsFound.add(found); 
			}
			if (soln.getWeapon() == found.getCardname()) {
				System.out.println("2nd");
				cardsFound.add(found); 
			}
			if (soln.getRoom() == found.getCardname()) {
				System.out.println("3rd");
				cardsFound.add(found); 
			}
			 
		}

		if (cardsFound.size() == 0) {
			return null;
		}
		
		else if (cardsFound.size() == 1) { 
			return cardsFound.get(0); 
		}
		
		else {
			Random rand = new Random(); 
			int possition = rand.nextInt(cardsFound.size());

			return cardsFound.get(possition); 
		}

	}

	public Solution getCreatedSoln() { 
		return createdSoln; 
	}

} 
