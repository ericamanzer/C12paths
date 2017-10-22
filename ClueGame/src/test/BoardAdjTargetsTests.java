/**
 * 
 */
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Set;

import org.junit.BeforeClass;
import clueGame.Board;
import clueGame.BoardCell;

/**
 * @author Demonna Wade and Erica Manzer
 *
 */
public class BoardAdjTargetsTests {
	// Making the board static so that only one copy of itself
	private static Board board;
	@BeforeClass
	public void setUp() throws Exception {
		board = Board.getInstance();
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.initialize();
		
		
		
		
		
		
		
		
	}

	/*
	 * Test Required: Adjacency
	 * - (1) Locations with only walkways as adjacent locations
	 * - (1) Locations within rooms aka, adjacency list should be empty
	 * - (4) Locations that are each edge of the board
	 * - (2) Locations that re beside a room cell that is not a doorway
	 * - (4) Locations that are adhacent to a doorway with needed direction. Testing all 4 directions. 
	 * - (2) Locations that are doorways, should have only one adjacent cell
	 * Test Required: Targets
	 * - (4) Targets along walkways
	 * - (2) Targets that allows the user to enter a room
	 * - (2) Targets calculated when leaving the room 
	 */
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	
	public void testAdjacenciesInsideRooms()
	{
		Set<BoardCell> testValue; 
		
		// Test a corner
		testValue = board.getAdjList(10, 4);
		assertEquals(0, testValue.size());
		// Test one that has walkway underneath
		testValue = board.getAdjList(16, 4);
		assertEquals(0, testValue.size());
		// Test one that has walkway above
		testValue = board.getAdjList(22, 15);
		assertEquals(0, testValue.size());
		// Test one that is in middle of room
		testValue = board.getAdjList(22, 5);
		assertEquals(0, testValue.size());
		// Test one beside a door
		testValue = board.getAdjList(1, 4);
		assertEquals(0, testValue.size());
		// Test one in a corner of room
		testValue = board.getAdjList(17, 15);
		assertEquals(0, testValue.size());
		
	}
	
	public void testAdjacencyRoomExit()
	{
		
		Set<BoardCell> testValue; 
		
		// TEST DOORWAY RIGHT 
		testValue = board.getAdjList(10, 19);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(11, 19)));
		// TEST DOORWAY LEFT 
		testValue = board.getAdjList(6, 20);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(5, 20)));
		//TEST DOORWAY DOWN
		testValue = board.getAdjList(7, 4);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(7, 5)));
		//TEST DOORWAY UP
		testValue = board.getAdjList(3, 17);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(3, 16)));
		//TEST DOORWAY RIGHT, WHERE THERE'S A WALKWAY BELOW
		testValue = board.getAdjList(1, 5);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(2, 5)));
		
	}
	
}
