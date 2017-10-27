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
	public static void setUp() throws Exception {
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
	 * - (4) Locations that are adjacent to a doorway with needed direction. Testing all 4 directions. 
	 * - (2) Locations that are doorways, should have only one adjacent cell
	 * Test Required: Targets
	 * - (4) Targets along walkways
	 * - (2) Targets that allows the user to enter a room
	 * - (2) Targets calculated when leaving the room 
	 */

	@Test
	public void testAdjacenciesInsideRooms() //passing, Green
	{
		Set<BoardCell> testValue; 
		
		// Test a corner
		testValue = board.getAdjList(10, 1); 
		assertEquals(0, testValue.size());
		// Test one that has walkway underneath
		testValue = board.getAdjList(3, 14);
		assertEquals(0, testValue.size());
		// Test one that has walkway above
		testValue = board.getAdjList(16, 12);
		assertEquals(0, testValue.size());
		// Test one that is in middle of room
		testValue = board.getAdjList(19, 21);
		assertEquals(0, testValue.size());
		// Test one beside a door
		testValue = board.getAdjList(3, 5);
		assertEquals(0, testValue.size());
		// Test one in a corner of room
		testValue = board.getAdjList(0, 0);
		assertEquals(0, testValue.size());
		
	}
	
	@Test
	public void testAdjacencyRoomExit() // PASSING, Red
	{
		
		Set<BoardCell> testValue; 
		
		// TEST DOORWAY UP
		testValue = board.getAdjList(13, 19);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(12, 19)));
		// TEST DOORWAY LEFT 
		testValue = board.getAdjList(15, 14);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(15, 13)));
		//TEST DOORWAY RIGHT
		testValue = board.getAdjList(18, 9);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(18, 10)));
		//TEST DOORWAY DOWN
		testValue = board.getAdjList(3, 6);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(4, 6)));
		//TEST DOORWAY RIGHT, WHERE THERE'S A WALKWAY BELOW
		testValue = board.getAdjList(9, 3);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(9, 4)));
		
	}
	
	@Test
	public void testAdjacencyDoorways() // Passing, Orange
	{
		
		Set<BoardCell> testValue; 
		
		// Test beside a door direction UP
		testValue = board.getAdjList(13, 18);
		assertEquals(3, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(12, 18)));
		assertTrue(testValue.contains(board.getCellAt(14, 18)));
		assertTrue(testValue.contains(board.getCellAt(13, 17)));
		
		// Test beside a door direction RIGHT
		testValue = board.getAdjList(18, 10);
		assertEquals(4, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(18, 11)));
		assertTrue(testValue.contains(board.getCellAt(18, 9)));
		assertTrue(testValue.contains(board.getCellAt(17, 10)));
		assertTrue(testValue.contains(board.getCellAt(19, 10)));
		// Test beside a door direction DOWN
		testValue = board.getAdjList(3, 13);
		assertEquals(3, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(2, 13)));
		assertTrue(testValue.contains(board.getCellAt(4, 13)));
		assertTrue(testValue.contains(board.getCellAt(3, 12)));
		// Test beside a door direction LEFT
		testValue = board.getAdjList(19, 4);
		assertEquals(3, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(18, 4)));
		assertTrue(testValue.contains(board.getCellAt(20, 4)));
		assertTrue(testValue.contains(board.getCellAt(19, 5)));
	}
	
	@Test
	public void testAdjacencyWalkways() // , Light Blue
	{
		
		Set<BoardCell> testValue; 
		
		// Test on top edge of board, just two walkway piece
		testValue = board.getAdjList(5, 0); 
		assertEquals(2, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(6, 0)));
		
		// Test on left edge of board, three walkway pieces
		testValue = board.getAdjList(13, 0);
		assertEquals(3, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(12, 0)));
		assertTrue(testValue.contains(board.getCellAt(14, 0)));
		assertTrue(testValue.contains(board.getCellAt(13, 1)));
		

		// Test between two rooms
		testValue = board.getAdjList(20, 10);
		assertEquals(3, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(20, 11)));
		assertTrue(testValue.contains(board.getCellAt(21, 10)));
		

		// Test surrounded by 3 walkways
		testValue = board.getAdjList(5, 10);
		assertTrue(testValue.contains(board.getCellAt(5, 9)));
		assertTrue(testValue.contains(board.getCellAt(5, 11)));
		assertTrue(testValue.contains(board.getCellAt(4, 10)));
		assertEquals(3, testValue.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testValue = board.getAdjList(21, 4);
		assertTrue(testValue.contains(board.getCellAt(20, 4)));
		assertEquals(1, testValue.size());
		
		// Test on right edge of board, next to 1 room piece
		testValue = board.getAdjList(12, 22);
		assertEquals(2, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(11, 22)));
		assertTrue(testValue.contains(board.getCellAt(12, 21)));
		
	}
	
	@Test
	public void testTargetsOneStep() { //Passing , Purple
		
		Set<BoardCell> targets; 
		
		board.calcTargets(6, 16, 1);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 16)));
		assertTrue(targets.contains(board.getCellAt(5, 16)));	
		
		board.calcTargets(21, 17, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(21, 18)));
		assertTrue(targets.contains(board.getCellAt(20, 17)));	
		
	}
	
	@Test
	public void testTargetsTwoSteps() { // , Light Green
		
		Set<BoardCell> targets; 
		
		board.calcTargets(0, 19, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 18)));
		
		board.calcTargets(12, 6, 2);
		targets= board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 6)));
					
	}
	
	@Test
	public void testTargetsFourSteps() { // Passing, Navy Blue
		
		Set<BoardCell> targets; 
		
		board.calcTargets(21, 18, 4);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 17)));
		assertTrue(targets.contains(board.getCellAt(19, 18)));
		assertTrue(targets.contains(board.getCellAt(18, 17)));
		assertTrue(targets.contains(board.getCellAt(17, 18)));
			
		
	}
	
	
	@Test
	public void testTargetsSixSteps() { // passing , blue
		
		board.calcTargets(13, 13, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(22, targets.size());
		assertTrue(targets.contains(board.getCellAt(12, 10)));
		assertTrue(targets.contains(board.getCellAt(12, 8)));	
		assertTrue(targets.contains(board.getCellAt(11, 17)));	
	}
	
	@Test 
	public void testTargetsIntoRoom() // Passing, Grey
	{
		
		// One room is exactly 2 away
		board.calcTargets(12, 16, 4);
		Set<BoardCell> targets= board.getTargets();
		//assertEquals(16, targets.size());
		assertTrue(targets.contains(board.getCellAt(11, 19)));
		assertTrue(targets.contains(board.getCellAt(9, 17)));
		// Last target tested is doorway 
		assertTrue(targets.contains(board.getCellAt(13, 19)));
		
	}
	
	@Test
	public void testTargetsIntoRoomShortcut() //Passing , Marron
	{
		board.calcTargets(4, 2, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 1)));
		assertTrue(targets.contains(board.getCellAt(4, 3)));
		assertTrue(targets.contains(board.getCellAt(5, 2)));		
	
		
	}
	
	@Test
	public void testRoomExit() // Passing, Yellow
	{
		
		// Take one step, essentially just the adj list
		board.calcTargets(2, 13, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(3, 13)));
		// Take two steps
		board.calcTargets(2, 13, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(3, 12)));
		assertTrue(targets.contains(board.getCellAt(4, 13)));
		
	}
	
}
