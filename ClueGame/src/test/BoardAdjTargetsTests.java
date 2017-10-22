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

}
