package test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import clueGame.*;

public class PlayerTest {

	@Test
	public void testLoadingPeople() {
		int peopleSize = getComputerPlayers().size() + getHumanPlayers().size(); 
		Set<HumanPlayer> TempHuman = new HashSet<HumanPlayer>(); 
		TempHuman = getHumanPlayer(); 
		assertEquals(6, peopleSize); 
		assertTrue("CompSci", TempHuman); 
		assertTrue("MechE", getComputerPlayers[0]); 
		assertTrue("ChemE", getComputerPlayers[1]); 
		assertTrue("Mining", getComputerPlayers[2]); 
		assertTrue("Geology", getComputerPlayers[3]); 
		assertTrue("Physics", getComputerPlayers[4]); 
		
	}

	@Test 
	public void testDeealCardsOut() {
		int deckSize = getDeck().size(); 
		int keySize = getKey().size(); 
		assertEquals(0, deckSize); 
		assertEquals(3, keySize);
		assertTrue(3, getHumanPlayer().getMyCardSize());
		assertTrue(3, getComputerPlayer().getMyCardSize()); 
	}
	
	
	
}
