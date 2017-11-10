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
		
		// fill up the seen cards of a player
		ComputerPlayer computerPlayer2 = new ComputerPlayer ("CompSci", "Blue", 14, 15);
		for (int i = 0; i < board.possiblePeople.size(); i++)
		{
			if ( )
			else
			{
				computerPlayer2.addSeen(board.possiblePeople.get(i));
				
			}
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
	
	//@Test 
	public void testDisproveSugesstion() { 
		
		BoardCell testCell = new BoardCell(5, 3, 't', DoorDirection.RIGHT); 
		ComputerPlayer computerPlayer = new ComputerPlayer(); 
		
		//computerPlayer.createSuggestion(testCell, board.possiblePeople, board.possibleWeapons, board.getRooms()); 
		
		Solution s1 = computerPlayer.getCreatedSoln(); 
		String p1 = s1.getPerson(); 
		String w1 = s1.getWeapon(); 
		String r1 = s1.getRoom(); 
	
		
		String p2 = "wrong"; 
		String w2 = "wrong"; 
		String r2 = r1; 
		
		Solution s2 = new Solution(p2, w2, r2); 
		
		Card card = computerPlayer.disproveSuggestion(s2); 
		String cardName = card.getCardname(); 
		
		
		
		assertTrue(cardName.equals(r2)); 
		
		
	}
	

}
