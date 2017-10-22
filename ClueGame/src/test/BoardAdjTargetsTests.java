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
	
	// Setting up the board to be used for the test.
	@BeforeClass
	public void setUp() throws Exception {
		
		
		
		
		
		
		
		
		
		
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
