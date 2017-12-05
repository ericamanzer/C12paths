/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */

package clueGame;

import java.util.*; 


public class HumanPlayer extends Player {
	
	private Solution accusation = new Solution();
	
	// Default constructor
	//@param no parameters
	//@return nothing return; default constructor
	public HumanPlayer()
	{
		super();
	}
	
	// Parameterized constructor
	//@param name player's name
	//@param r player's location via row
	//@param c player's location via column
	//@param color player's color that is read as a string from a file
	public HumanPlayer(String name, String color, int r, int c)
	{
		super(name, color, r, c);
	}
	
	public void setAccusation( String room, String person, String weapon)
	{
		accusation.setAnswerKeyPerson(person);
		accusation.setAnswerKeyRoom(room);
		accusation.setAnswerKeyWeapon(weapon);
	}
	
	
	@Override
	public Card disproveSuggestion(Solution soln) {

		//String p1, p2, w1, w2, r1, r2;  

		Set<Card> myCards = new HashSet<Card>(); 
		myCards = getMyCards(); 
		ArrayList<Card> cardsFound = new ArrayList<Card>();
		//System.out.println(myCards.size());
		for (Card found: myCards) {
			if (soln.getPerson() == found.getCardname()) {
				//System.out.println("1st");
				cardsFound.add(found); 
			}
			if (soln.getWeapon() == found.getCardname()) {
				//System.out.println("2nd");
				cardsFound.add(found); 
			}
			if (soln.getRoom() == found.getCardname()) {
				//System.out.println("3rd");
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
	
}
