package test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import clueGame.*;

public class PlayerTest {
	private static Board board;
	@Test
	public void testLoadingPeople() {
		int peopleSize = board.getComputerPlayers().size() + board.getHumanPlayer().size(); 
		Set<HumanPlayer> TempHuman = new HashSet<HumanPlayer>(); 
		TempHuman = board.getHumanPlayer(); 
		assertEquals(6, peopleSize); 
		assertTrue("CompSci", TempHuman);   // change 
		assertTrue("MechE", getComputerPlayers[0]); 
		assertTrue("ChemE", getComputerPlayers[1]); 
		assertTrue("Mining", getComputerPlayers[2]); 
		assertTrue("Geology", getComputerPlayers[3]); 
		assertTrue("Physics", getComputerPlayers[4]); 
		
	}

	@Test 
	public void testDeealCardsOut() {
		int deckSize = board.getDeck().size(); 
		int keySize = board.getKey().size(); 
		assertEquals(0, deckSize); 
		assertEquals(3, keySize);
		assertTrue(3, board.getHumanPlayer().getMyCardSize());
		assertTrue(3, board.getComputerPlayer().getMyCardSize()); 
	}
	
	
	
}
