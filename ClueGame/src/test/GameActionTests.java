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

	//@Test

	public void testCreateSuggestion()
	{

		ComputerPlayer computerPlayer = new ComputerPlayer ("CompSci","Blue", 14, 15);
		computerPlayer.createSuggestion(board.getCellAt(14, 15), board.possiblePeople, board.possibleWeapons, board.getRooms(), computerPlayer);
		// will be comparing things to the Solution class which uses strings
		Solution sol = computerPlayer.getCreatedSoln();
		String solutionRoom = sol.getRoom();
		// room matches current location
		assertEquals("Guggenheim", solutionRoom);

		// fill up the seen cards of a player
		ComputerPlayer computerPlayer2 = new ComputerPlayer ("CompSci", "Blue", 14, 15);
		for (int i = 0; i < board.possiblePeople.size(); i++)
		{
			//if ( )
			//else
			//{
			//computerPlayer2.addSeen(board.possiblePeople.get(i));

			//}
		}
		for (int i = 0; i < board.possibleWeapons.size(); i++)
		{
			computerPlayer2.addSeen(board.possibleWeapons.get(i));
			if ( i == board.possibleWeapons.size() - 2) break;
		}
		System.out.println( " Got here ");
		Card answerWeapon = board.possibleWeapons.get(board.possibleWeapons.size() - 1);
		computerPlayer2.createSuggestion(board.getCellAt(14, 15), board.possiblePeople, board.possibleWeapons, board.getRooms(), computerPlayer2);

		Solution sol2 = computerPlayer.getCreatedSoln();
		String solutionWeapon = sol2.getWeapon();
		System.out.println(answerWeapon + " " + solutionWeapon);
		assertEquals(answerWeapon, solutionWeapon);

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
