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
	public void setUp() throws Exception {
		board = Board.getInstance();
		//set the file names to be used 
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.setWeaponsConfigFile("WeaponsConfig.txt");
		board.setPeopleConfigFile("PeopleConfig.txt");
		
		board.initialize();
	}

	@Test
	public void testSelectingATarget() {
		fail("Not yet implemented");
		
		//should only choose a valid target; calculating targets already tested
		board.calcTargets(4, 2, 3);
		// save current location into BoardCell previous
		BoardCell current =  board.getCellAt(4, 2);
		Player computer = new Player();
		computer.savingTestLocation(current);
		
		Set<BoardCell> targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 1)));
		assertTrue(targets.contains(board.getCellAt(4, 3)));
		assertTrue(targets.contains(board.getCellAt(5, 2)));
		
		//HELLO
		// If no rooms in list, choose a target randomnly 
		
	}

}
