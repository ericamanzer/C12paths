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

		// testing to see if the selected room is equal to the correct room (the room it should be)
		BoardCell correctSelection = new BoardCell (13, 19, 'B', DoorDirection.UP );
		
		// need to call ComputerPlayer with the pickLocation function and return the selected position
		ComputerPlayer computer = new ComputerPlayer();
		BoardCell selected = computer.pickLocation(targets);
		assertEquals(board.getCellAt(13, 19), selected);

	}

}
