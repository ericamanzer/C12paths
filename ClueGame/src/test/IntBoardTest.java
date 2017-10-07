package test;

import static org.junit.Assert.*;
import experiment.*;
import org.junit.Test;
import java.util.*;
import org.junit.Before;


public class IntBoardTest {
	private IntBoard intBoard;
	// Before java tag that sets up the board for the Java Unit test
	 @Before
	 public void IntBoardTest() 
	 {
		 intBoard = new IntBoard();
		 intBoard.calcAdjancies();
	 }
	 
	@Test
	public void testAdjacency_0()
	{
		
		BoardCell cell = intBoard.getCell(0,0);
		Map<BoardCell, Set<BoardCell>> map = intBoard.getAdjList();
		Set<BoardCell> testList = map.get(cell);
		assertTrue(testList.contains(intBoard.getCell(0, 0)));
		assertTrue(testList.contains(intBoard.getCell(3, 3)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testAdjacency_1()
	{
		BoardCell cell = intBoard.getCell(0,0);
		Map<BoardCell, Set<BoardCell>> map = intBoard.getAdjList();
		Set<BoardCell> testList = map.get(cell);
		assertTrue(testList.contains(intBoard.getCell(1, 3)));
		assertTrue(testList.contains(intBoard.getCell(3, 0)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testAdjacency_2()
	{
		BoardCell cell = intBoard.getCell(0,0);
		Map<BoardCell, Set<BoardCell>> map = intBoard.getAdjList();
		Set<BoardCell> testList = map.get(cell);
		assertTrue(testList.contains(intBoard.getCell(1, 1)));
		assertTrue(testList.contains(intBoard.getCell(2, 2)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testAdjacency_3()
	{
		BoardCell cell = intBoard.getCell(0,0);
		Map<BoardCell, Set<BoardCell>> map = intBoard.getAdjList();
		Set<BoardCell> testList = map.get(cell);
		assertTrue(testList.contains(intBoard.getCell(3, 1)));
		assertTrue(testList.contains(intBoard.getCell(2, 2)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testAdjacency_4()
	{
		BoardCell cell = intBoard.getCell(0,0);
		Map<BoardCell, Set<BoardCell>> map = intBoard.getAdjList();
		Set<BoardCell> testList = map.get(cell);
		assertTrue(testList.contains(intBoard.getCell(3, 1)));
		assertTrue(testList.contains(intBoard.getCell(2, 0)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testAdjacency_5()
	{
		BoardCell cell = intBoard.getCell(0,0);
		Map<BoardCell, Set<BoardCell>> map = intBoard.getAdjList();
		Set<BoardCell> testList = map.get(cell);
		assertTrue(testList.contains(intBoard.getCell(3, 2)));
		assertTrue(testList.contains(intBoard.getCell(2, 0)));
		assertEquals(2, testList.size());
		
	}



}
