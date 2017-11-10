package test;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;
import org.junit.Test;
import clueGame.*;

public class GameActionTests {
	// Making the board static so that only one copy of itself
	private static Board board;
	@BeforeClass
	public static void setUp() throws Exception {
		board = Board.getInstance();
		//set the file names to be used 
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.setWeaponsConfigFile("WeaponsConfig.txt");
		board.setPeopleConfigFile("PeopleConfig.txt");

		board.initialize();
	}
	@Test
	public void testCreateSuggestion()
	{

		ComputerPlayer computerPlayer = new ComputerPlayer ("CompSci","Blue", 14, 15);
		computerPlayer.createSuggestion(board.getCellAt(14, 15), board.possiblePeople, board.possibleWeapons, board.getRooms(), computerPlayer);
		// will be comparing things to the Solution class which uses strings
		Solution sol = computerPlayer.getCreatedSoln();
		String solutionRoom = sol.getRoom();
		// room matches current location
		assertEquals("Guggenheim", solutionRoom);

		// Making sure null was returned 
		computerPlayer.clearSeenCards();
		for ( Card seen : board.possiblePeople)
		{
			computerPlayer.addSeen(seen);
		}
		for ( Card seen : board.possibleWeapons)
		{
			computerPlayer.addSeen(seen);
		}
		computerPlayer.createSuggestion(board.getCellAt(14, 15), board.possiblePeople, board.possibleWeapons, board.getRooms(), computerPlayer);
		sol = computerPlayer.getCreatedSoln();
		String solution = sol.getPerson();
		assertNull(solution);

		// If only one people not seen, it's selected; 
		computerPlayer.clearSeenCards();
		ArrayList<Card> weapons = new ArrayList<Card>();
		ArrayList<Card> people = new ArrayList<Card>();
		Card card = new Card("Keyboard", CardType.WEAPON);
		Card card2 = new Card ("MatLab", CardType.WEAPON);
		Card card3 = new Card ("Chemical", CardType.WEAPON);
		Card card4 = new Card ("Pickaxe", CardType.WEAPON);
		Card card5 = new Card ("Rock", CardType.WEAPON);
		Card card6 = new Card ("Exams", CardType.WEAPON);
		Card card7 = new Card ("CompSci", CardType.PERSON);
		Card card8 = new Card ("MechE", CardType.PERSON);
		Card card9 = new Card ("ChemE", CardType.PERSON);
		Card card10 = new Card ("Mining", CardType.PERSON);
		Card card11 = new Card ("Geology", CardType.PERSON);
		Card card12 = new Card ("Physics", CardType.PERSON);
		computerPlayer.addSeen(card);
		computerPlayer.addSeen(card2);
		computerPlayer.addSeen(card3);
		computerPlayer.addSeen(card4);
		computerPlayer.addSeen(card5);
		computerPlayer.addSeen(card6);
		computerPlayer.addSeen(card7);
		computerPlayer.addSeen(card8);
		computerPlayer.addSeen(card9);
		computerPlayer.addSeen(card10);
		computerPlayer.addSeen(card11);
		weapons.add(card);
		weapons.add(card2);
		weapons.add(card3);
		weapons.add(card4);
		weapons.add(card5);
		weapons.add(card6);
		people.add(card7);
		people.add(card8);
		people.add(card9);
		people.add(card10);
		people.add(card11);
		people.add(card12);
		System.out.println("Seen cards: " + computerPlayer.getSeenCards().size());
		computerPlayer.createSuggestion(board.getCellAt(14, 15), people, weapons, board.getRooms(), computerPlayer);
		sol = computerPlayer.getCreatedSoln();
		solution = sol.getPerson();
		System.out.println(card12.getCardname() + " " + solution);
		assertEquals(card12.getCardname(), solution);
		
		System.out.println("HERE !!!!");
		//If only one weapon not seen, it's selected
		computerPlayer.clearSeenCards();
		System.out.println("Seen cards: " + computerPlayer.getSeenCards().size());
		ArrayList<Card> weapons2 = new ArrayList<Card>();
		ArrayList<Card> people2 = new ArrayList<Card>();
		computerPlayer.addSeen(card2);
		computerPlayer.addSeen(card3);
		computerPlayer.addSeen(card4);
		computerPlayer.addSeen(card5);
		computerPlayer.addSeen(card6);
		computerPlayer.addSeen(card7);
		computerPlayer.addSeen(card8);
		computerPlayer.addSeen(card9);
		computerPlayer.addSeen(card10);
		computerPlayer.addSeen(card11);
		computerPlayer.addSeen(card12);
		weapons2.add(card);
		weapons2.add(card2);
		weapons2.add(card3);
		weapons2.add(card4);
		weapons2.add(card5);
		weapons2.add(card6);
		people2.add(card7);
		people2.add(card8);
		people2.add(card9);
		people2.add(card10);
		people2.add(card11);
		people2.add(card12);
		System.out.println("Seen cards: " + computerPlayer.getSeenCards().size());
		computerPlayer.createSuggestion(board.getCellAt(14, 15), people2, weapons2, board.getRooms(), computerPlayer);
		sol = computerPlayer.getCreatedSoln();
		solution = sol.getWeapon();
		System.out.println(card.getCardname() + " " + solution);

		assertEquals(card.getCardname(), solution);
	}

	@Test
	public void testSelectingATarget() {
		// One room is exactly 2 away
		board.calcTargets(12, 16, 4);
		// save current location into BoardCell previous; may need or may not
		//BoardCell current =  board.getCellAt(12, 16);
		//Player computer = new Player();
		//computer.savingTestLocation(current);

		// will be passing in targets into the pickLocation function that is called by ComputerPlayer which extends player
		Set<BoardCell> targets= board.getTargets();
		// testing the calculated targets 
		//assertEquals(16, targets.size());
		assertTrue(targets.contains(board.getCellAt(11, 19)));
		assertTrue(targets.contains(board.getCellAt(9, 17)));
		// Last target tested is doorway 
		assertTrue(targets.contains(board.getCellAt(13, 19)));

		// need to call ComputerPlayer with the pickLocation function and return the selected position
		ComputerPlayer computer = new ComputerPlayer();
		BoardCell selected = computer.pickLocation(targets);
		assertEquals(board.getCellAt(13, 19), selected);

	}


	@Test 
	public void testAccusation() {
		Solution answerKey = board.getAnswerKey(); 
		String ansP = answerKey.getPerson(); 
		String ansW = answerKey.getWeapon(); 
		String ansR = answerKey.getRoom(); 

		Solution accusation = new Solution();

		// Solution that is correct 
		accusation.setAnswerKeyPerson(ansP);
		accusation.setAnswerKeyWeapon(ansW); 
		accusation.setAnswerKeyRoom(ansR); 

		assertTrue(board.checkAccusation(accusation));

		// Solution with wrong person 
		accusation.setAnswerKeyPerson("wrong");

		assertFalse(board.checkAccusation(accusation));

		// Solution with wrong weapon 
		accusation.setAnswerKeyPerson(ansP);
		accusation.setAnswerKeyWeapon("wrong");

		assertFalse(board.checkAccusation(accusation)); 

		// Solution with wrong room 
		accusation.setAnswerKeyWeapon(ansW);
		accusation.setAnswerKeyRoom("wrong");

		assertFalse(board.checkAccusation(accusation)); 

	}

	@Test 
	public void testDisproveSugesstion() { 

		//BoardCell testCell = new BoardCell(5, 3, 't', DoorDirection.RIGHT); 
		Set<ComputerPlayer> computerPlayers = new HashSet<ComputerPlayer>();  
		computerPlayers = board.getComputerPlayers(); 

		ComputerPlayer computerPlayer = new ComputerPlayer(); 
		ComputerPlayer testPlayer = new ComputerPlayer();

		int counter = 0; 
		for (ComputerPlayer temp: computerPlayers) {
			if (counter == 0) {
				computerPlayer = temp; 
			}
			else if (counter == 1) {
				testPlayer = temp; 
			}
			else {
				break;
			} 
			counter ++; 
		}


		computerPlayer.createSuggestion(board.getCellAt(4, 0), board.possiblePeople, board.possibleWeapons, board.getRooms(), computerPlayer); 

		testPlayer.clearCards();

		Solution s1 = computerPlayer.getCreatedSoln(); 
		String p1 = s1.getPerson(); 
		Card cp1 = new Card(p1, CardType.PERSON); 
		String w1 = s1.getWeapon(); 
		Card cw1 = new Card(w1, CardType.WEAPON);
		String r1 = s1.getRoom(); 
		Card cr1 = new Card(r1, CardType.ROOM);

		testPlayer.addCard(cp1);
		testPlayer.addCard(cw1);
		testPlayer.addCard(cr1);

		String p2 = "wrong"; 
		String w2 = w1; 
		String r2 = "wrong"; 

		Solution s2 = new Solution(p2, w2, r2); 


		Card returnedCard = testPlayer.disproveSuggestion(s2); 
		Card test = new Card(w1, CardType.WEAPON);

		String a = test.getCardname(); 
		String b = returnedCard.getCardname(); 

		System.out.println("A: " + a);
		System.out.println("B: " + b);


		// One matching card
		assertTrue(a.equals(b)); 

		p2 = p1; 
		r2 = r1; 

		Solution s3 = new Solution(p2, w2, r2); 

		returnedCard = testPlayer.disproveSuggestion(s3);
		Card testP = new Card(p2, CardType.PERSON); 
		Card testW = new Card(w2, CardType.WEAPON); 
		Card testR = new Card(r2, CardType.ROOM); 

		b = returnedCard.getCardname(); 
		String c = testP.getCardname(); 
		String d = testW.getCardname(); 
		String e = testR.getCardname(); 
		System.out.println(b + " " + c + " " + d + " " + e);

		// More than one matching card 
		assertTrue(b.equals(c) || b.equals(d) || b.equals(e));


		p2 = "wrong"; 
		w2 = "wrong"; 
		r2 = "wrong"; 

		Solution s4 = new Solution(p2, w2, r2); 

		assertNull(testPlayer.disproveSuggestion(s4));


	}

	@Test
	public void testHandleSuggestions() { 
		//Suggestion no one can disprove returns null


		Set<ComputerPlayer> computerPlayers = new HashSet<ComputerPlayer>();  
		computerPlayers = board.getComputerPlayers(); 

		ComputerPlayer computerPlayer = new ComputerPlayer(); 


		for (ComputerPlayer temp: computerPlayers) {

			computerPlayer = temp; 
			break; 
		}

		//computerPlayer.clearCards(); 

		//Suggestion only accusing player can disprove returns null

		Set<Card> myCards = new HashSet<Card>(); 
		computerPlayer.getMyCards();
		ArrayList<Card> possibleCards = new ArrayList<Card>(); 
		possibleCards.addAll(board.possiblePeople); 
		possibleCards.addAll(board.possibleRooms); 
		possibleCards.addAll(board.possibleWeapons);
		for (Card temp: myCards) { 

		}

		System.out.println("Mycards.size(): " + myCards.size());

		//Suggestion only human can disprove returns answer (i.e., card that disproves suggestion)
		//Suggestion only human can disprove, but human is accuser, returns null
		//Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		//Suggestion that human and another player can disprove, other player is next in list, ensure other player returns answer
	}

}
