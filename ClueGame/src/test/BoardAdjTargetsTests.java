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
	 * - (4) Locations that are adjacent to a doorway with needed direction. Testing all 4 directions. 
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

	@Test
	public void testAdjacenciesInsideRooms()
	{
		Set<BoardCell> testValue; 
		
		// Test a corner
		testValue = board.getAdjList(9, 3);
		assertEquals(0, testValue.size());
		// Test one that has walkway underneath
		testValue = board.getAdjList(15, 3);
		assertEquals(0, testValue.size());
		// Test one that has walkway above
		testValue = board.getAdjList(21, 14);
		assertEquals(0, testValue.size());
		// Test one that is in middle of room
		testValue = board.getAdjList(21, 4);
		assertEquals(0, testValue.size());
		// Test one beside a door
		testValue = board.getAdjList(0, 3);
		assertEquals(0, testValue.size());
		// Test one in a corner of room
		testValue = board.getAdjList(16, 14);
		assertEquals(0, testValue.size());
		
	}
	
	@Test
	public void testAdjacencyRoomExit()
	{
		
		Set<BoardCell> testValue; 
		
		// TEST DOORWAY RIGHT 
		testValue = board.getAdjList(9, 18);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(10, 18)));
		// TEST DOORWAY LEFT 
		testValue = board.getAdjList(5, 19);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(4, 19)));
		//TEST DOORWAY DOWN
		testValue = board.getAdjList(6, 3);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(6, 4)));
		//TEST DOORWAY UP
		testValue = board.getAdjList(2, 16);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(2, 15)));
		//TEST DOORWAY RIGHT, WHERE THERE'S A WALKWAY BELOW
		testValue = board.getAdjList(0, 4);
		assertEquals(1, testValue.size());
		assertTrue(testValue.contains(board.getCellAt(1, 5)));
		
	}
	
	@Test
	public void testAdjacencyDoorways()
	{
		
		Set<BoardCell> testValue; 
		
		// Test beside a door direction RIGHT
		testValue = board.getAdjList(10, 18);
		assertTrue(testValue.contains(board.getCellAt(11, 18)));
		assertTrue(testValue.contains(board.getCellAt(10, 17)));
		assertTrue(testValue.contains(board.getCellAt(10, 19)));
		assertTrue(testValue.contains(board.getCellAt(9, 20)));
		assertEquals(4, testValue.size());
		// Test beside a door direction DOWN
		testValue = board.getAdjList(6, 3);
		assertTrue(testValue.contains(board.getCellAt(6, 4)));
		assertTrue(testValue.contains(board.getCellAt(6, 2)));
		assertTrue(testValue.contains(board.getCellAt(7, 3)));
		assertTrue(testValue.contains(board.getCellAt(5, 3)));
		assertEquals(4, testValue.size());
		// Test beside a door direction LEFT
		testValue = board.getAdjList(19, 7);
		assertTrue(testValue.contains(board.getCellAt(19, 8)));
		assertTrue(testValue.contains(board.getCellAt(19, 6)));
		assertTrue(testValue.contains(board.getCellAt(18, 7)));
		assertTrue(testValue.contains(board.getCellAt(20, 7)));
		assertEquals(4, testValue.size());
		// Test beside a door direction UP
		testValue = board.getAdjList(19, 12);
		assertTrue(testValue.contains(board.getCellAt(19, 11)));
		assertTrue(testValue.contains(board.getCellAt(19, 13)));
		assertTrue(testValue.contains(board.getCellAt(18, 12)));
		assertTrue(testValue.contains(board.getCellAt(20, 12)));
		assertEquals(4, testValue.size());
	}
	
	@Test
	public void testAdjacencyWalkways()
	{
		
		Set<BoardCell> testValue; 
		
		// Test on top edge of board, just one walkway piece
		testValue = board.getAdjList(3, 0); 
		assertTrue(testValue.contains(board.getCellAt(3, 1)));
		assertEquals(1, testValue.size());
		
		// Test on left edge of board, three walkway pieces
		testValue = board.getAdjList(0, 13);
		assertTrue(testValue.contains(board.getCellAt(0, 12)));
		assertTrue(testValue.contains(board.getCellAt(0, 15)));
		assertTrue(testValue.contains(board.getCellAt(1, 13)));
		assertEquals(3, testValue.size());

		// Test between two rooms, walkways up and down 
		testValue = board.getAdjList(19, 2);
		assertTrue(testValue.contains(board.getCellAt(19, 1)));
		assertTrue(testValue.contains(board.getCellAt(19, 3)));
		assertEquals(2, testValue.size());

		// Test surrounded by 4 walkways
		testValue = board.getAdjList(5, 10);
		assertTrue(testValue.contains(board.getCellAt(5, 9)));
		assertTrue(testValue.contains(board.getCellAt(5, 11)));
		assertTrue(testValue.contains(board.getCellAt(4, 10)));
		assertTrue(testValue.contains(board.getCellAt(6, 10)));
		assertEquals(4, testValue.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testValue = board.getAdjList(17, 21);
		assertTrue(testValue.contains(board.getCellAt(18, 21)));
		assertTrue(testValue.contains(board.getCellAt(17, 20)));
		assertEquals(2, testValue.size());
		
		// Test on right edge of board, next to 1 room piece
		testValue = board.getAdjList(0, 6);
		assertTrue(testValue.contains(board.getCellAt(0, 5)));
		assertTrue(testValue.contains(board.getCellAt(1, 6)));
		assertEquals(2, testValue.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testValue = board.getAdjList(18, 13);
		assertTrue(testValue.contains(board.getCellAt(18, 12)));
		assertTrue(testValue.contains(board.getCellAt(18, 14)));
		assertTrue(testValue.contains(board.getCellAt(17, 13)));
		assertEquals(3, testValue.size());
	}
	
	@Test
	public void testTargetsOneStep() {
		
		Set<BoardCell> targets; 
		
		board.calcTargets(7, 15, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 14)));
		assertTrue(targets.contains(board.getCellAt(6, 15)));	
		
		board.calcTargets(11, 0, 1);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(11, 1)));
		assertTrue(targets.contains(board.getCellAt(10, 0)));	
		assertTrue(targets.contains(board.getCellAt(12, 0)));	
		
	}
	
	@Test
	public void testTargetsTwoSteps() {
		
		Set<BoardCell> targets; 
		
		board.calcTargets(22, 9, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(22, 11)));
		assertTrue(targets.contains(board.getCellAt(21, 10)));
		assertTrue(targets.contains(board.getCellAt(20, 9)));
		
		board.calcTargets(19, 0, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 2)));
		assertTrue(targets.contains(board.getCellAt(18, 1)));
					
	}
	
	@Test
	public void testTargetsFourSteps() {
		
		Set<BoardCell> targets; 
		
		board.calcTargets(11, 21, 4);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 18)));
		assertTrue(targets.contains(board.getCellAt(10, 20)));
		assertTrue(targets.contains(board.getCellAt(11, 17)));
		assertTrue(targets.contains(board.getCellAt(11, 19)));
		
		// Includes a path that doesn't have enough length
		board.calcTargets(4, 21, 4);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 18)));
		assertTrue(targets.contains(board.getCellAt(5, 20)));	
		
	}
	
	
	@Test
	public void testTargetsSixSteps() {
		
		board.calcTargets(18, 21, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(18, 15)));
		assertTrue(targets.contains(board.getCellAt(18, 17)));	
		assertTrue(targets.contains(board.getCellAt(18, 19)));	
		assertTrue(targets.contains(board.getCellAt(17, 20)));	
		assertTrue(targets.contains(board.getCellAt(17, 18)));	
		assertTrue(targets.contains(board.getCellAt(17, 16)));	
		
	}
	
	@Test 
	public void testTargetsIntoRoom()
	{
		
		// One room is exactly 2 away
		board.calcTargets(12, 15, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 15)));
		assertTrue(targets.contains(board.getCellAt(11, 16)));
		assertTrue(targets.contains(board.getCellAt(11, 14)));
		assertTrue(targets.contains(board.getCellAt(12, 13)));
		assertTrue(targets.contains(board.getCellAt(13, 14)));
		// Last target tested is doorway 
		assertTrue(targets.contains(board.getCellAt(14, 15)));
		
	}
	
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(2, 4, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(10, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 4)));
		assertTrue(targets.contains(board.getCellAt(0, 5)));
		assertTrue(targets.contains(board.getCellAt(1, 4)));
		assertTrue(targets.contains(board.getCellAt(1, 6)));
		assertTrue(targets.contains(board.getCellAt(2, 5)));
		assertTrue(targets.contains(board.getCellAt(3, 2)));
		assertTrue(targets.contains(board.getCellAt(3, 4)));
		assertTrue(targets.contains(board.getCellAt(3, 6)));
		assertTrue(targets.contains(board.getCellAt(4, 5)));
		assertTrue(targets.contains(board.getCellAt(5, 4)));		
	
		
	}
	
	@Test
	public void testRoomExit()
	{
		
		// Take one step, essentially just the adj list
		board.calcTargets(13, 2, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 3)));
		// Take two steps
		board.calcTargets(13, 2, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 4)));
		assertTrue(targets.contains(board.getCellAt(12, 3)));
		
	}
	
}
