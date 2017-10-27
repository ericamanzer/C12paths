package test;

import static org.junit.Assert.*;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import clueGame.*;

public class FileInitTest {
	// Constants for testing
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 23;
	public static final int NUM_COLUMNS = 23;
	
	private static Board clueBoard;   //Board static
	
	@BeforeClass
	public static void createBoard() 
	{
		clueBoard = Board.getInstance();
		clueBoard.setConfigFiles("C12 Layout.csv", "C12 Layout.txt");	
		clueBoard.initialize();
	}

	@Test
	public void testRoomDoors() 
	{
		// Testing Doors to see if they were recorded as room as well as Doors
		assertEquals('M', clueBoard.getCellAt(11, 0).getInitial());
		assertEquals('R', clueBoard.getCellAt(16, 2).getInitial());
		assertEquals('A', clueBoard.getCellAt(3, 6).getInitial());
		// Testing the door direction
		assertEquals(DoorDirection.RIGHT, clueBoard.getCellAt(11, 0).getDoorDirection());
		assertEquals(DoorDirection.UP, clueBoard.getCellAt(16, 2).getDoorDirection());
		assertEquals(DoorDirection.DOWN, clueBoard.getCellAt(3, 6).getDoorDirection());	
	}
	
	@Test
	public void testNotDoorCount()
	{
		// testing the door count based of the amount of doors that are doorDirectins.NONE
		int door = 0, notDoor = 0;
		//BoardCell[] array = new BoardCell[NUM_COLUMNS];
		for (int x = 0; x < NUM_ROWS; x++)
		{
			for (int y = 0; y < NUM_COLUMNS; y++)
			{
				BoardCell cell = clueBoard.getCellAt(x, y);
				if (cell.getDoorDirection() == DoorDirection.NONE)
				{
					notDoor++;
				}
				if (cell.isDoorway()) door++;
			}
		}
		int shouldBe = (NUM_ROWS * NUM_COLUMNS) - door;
		assertEquals(shouldBe, notDoor);
		
	}
	
	@Test
	public void verifyDoorDirection()
	{
		// testing the board directions with counters and a boolean test
		int right =0, left = 0, up = 0, down = 0;
		boolean foundDir = false;
		for (int i = 0; i < clueBoard.getNumRows(); i++)
		{
			for (int j = 0; j < clueBoard.getNumColumns(); j++)
			{
				if (clueBoard.getCellAt(i, j).getDoorDirection() == DoorDirection.DOWN) down++;
				if (clueBoard.getCellAt(i, j).getDoorDirection() == DoorDirection.UP) up++;
				if (clueBoard.getCellAt(i, j).getDoorDirection() == DoorDirection.LEFT) left++;
				if (clueBoard.getCellAt(i, j).getDoorDirection() == DoorDirection.RIGHT) right++;
			}
		}
		if (down >=1 && up>= 1 && left >=1 && right >=1)
		{
			foundDir = true;
		}
		assertTrue(foundDir);
	}
	
	@Test
	public void testBoardDim()
	{
		// test the board dimensions useing to assertTrue with two booleans
		boolean col = false, row = false;
		if (NUM_ROWS == clueBoard.getNumRows()){ row = true;}
		if (NUM_COLUMNS == clueBoard.getNumColumns()){ col = true;} 
		assertTrue(row);
		assertTrue(col);
	}
	
	@Test
	public void testLegend()
	{
		// testing the legends using the initial characters from the board
		Map<Character, String> leg = clueBoard.getLegend();
		
		char letter = clueBoard.getCellAt(0, 0).getInitial();
		assertEquals("Weaver", leg.get(letter));
		
		char letterTwo = clueBoard.getCellAt(6, 8).getInitial();
		assertEquals("Kafadar", leg.get(letterTwo));
	}

}
