/*
 * Author: Demonna Wade and Erica Manzer 
 */


package test;

import static org.junit.Assert.*;
import experiment.*;
import org.junit.Test;

import clueGame.BoardCell_CR;

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
	 
	 // Test Adjacency ( 6 test total )
	@Test
	public void testAdjacency_0()
	{

		BoardCell_CR cell = new BoardCell_CR();
		cell = intBoard.getCell(0,0);
		Set<BoardCell_CR> testList = intBoard.getAdjList(cell);
		assertTrue(testList.contains(intBoard.getCell(0, 1)));
		assertTrue(testList.contains(intBoard.getCell(1, 0)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	public void testAdjacency_1()
	{
		BoardCell_CR cell = new BoardCell_CR();
		cell = intBoard.getCell(1,1);
		Set<BoardCell_CR> testList = intBoard.getAdjList(cell);
		assertTrue(testList.contains(intBoard.getCell(0, 1)));
		assertTrue(testList.contains(intBoard.getCell(1, 0)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency_2()
	{
		BoardCell_CR cell = new BoardCell_CR();
		cell = intBoard.getCell(1,0);
		Set<BoardCell_CR> testList = intBoard.getAdjList(cell);
		assertTrue(testList.contains(intBoard.getCell(0, 0)));
		assertTrue(testList.contains(intBoard.getCell(1, 1)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency_3()
	{
		BoardCell_CR cell = new BoardCell_CR();
		cell = intBoard.getCell(2,1);
		Set<BoardCell_CR> testList = intBoard.getAdjList(cell);
		assertTrue(testList.contains(intBoard.getCell(1, 1)));
		assertTrue(testList.contains(intBoard.getCell(2, 0)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency_4()
	{
		BoardCell_CR cell = new BoardCell_CR();
		cell = intBoard.getCell(2,3);
		Set<BoardCell_CR> testList = intBoard.getAdjList(cell);
		assertTrue(testList.contains(intBoard.getCell(1, 3)));
		assertTrue(testList.contains(intBoard.getCell(2, 2)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency_5()
	{
		BoardCell_CR cell = new BoardCell_CR();
		cell = intBoard.getCell(3,0);
		Set<BoardCell_CR> testList = intBoard.getAdjList(cell);
		assertTrue(testList.contains(intBoard.getCell(2, 0)));
		assertTrue(testList.contains(intBoard.getCell(3, 1)));
		assertEquals(2, testList.size());
		
	}

	// Test CalTarget ( 6 test total )
	@Test
	public void testTartgets0_0()
	{
		BoardCell_CR cell = intBoard.getCell(0, 1);
		intBoard.calcTargets(cell, 2);
		Set<BoardCell_CR> targets = intBoard.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(intBoard.getCell(0, 1)));
		assertTrue(targets.contains(intBoard.getCell(1, 2)));
		assertTrue(targets.contains(intBoard.getCell(0, 3)));
	}
	
	@Test
	public void testTartgets0_1()
	{
		BoardCell_CR cell = intBoard.getCell(0, 0);
		intBoard.calcTargets(cell, 1);
		Set<BoardCell_CR> targets = intBoard.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(intBoard.getCell(0, 1)));
		assertTrue(targets.contains(intBoard.getCell(1, 0)));
	}
	
	@Test
	public void testTartgets0_2()
	{
		BoardCell_CR cell = intBoard.getCell(3, 1);
		intBoard.calcTargets(cell, 4);
		Set<BoardCell_CR> targets = intBoard.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(intBoard.getCell(0, 0)));
		assertTrue(targets.contains(intBoard.getCell(2, 0)));
		assertTrue(targets.contains(intBoard.getCell(1, 1)));
	}
	
	@Test
	public void testTartgets0_3()
	{
		BoardCell_CR cell = intBoard.getCell(0, 3);
		intBoard.calcTargets(cell, 3);
		Set<BoardCell_CR> targets = intBoard.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(intBoard.getCell(2, 2)));
		assertTrue(targets.contains(intBoard.getCell(0, 0)));
		assertTrue(targets.contains(intBoard.getCell(3, 3)));
	}
	
	@Test
	public void testTartgets0_4()
	{
		BoardCell_CR cell = intBoard.getCell(0, 3);
		intBoard.calcTargets(cell, 2);
		Set<BoardCell_CR> targets = intBoard.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(intBoard.getCell(0, 1)));
		assertTrue(targets.contains(intBoard.getCell(1, 2)));
		assertTrue(targets.contains(intBoard.getCell(0, 3)));
	}
	
	@Test
	public void testTartgets0_5()
	{
		BoardCell_CR cell = intBoard.getCell(3, 3);
		intBoard.calcTargets(cell, 3);
		Set<BoardCell_CR> targets = intBoard.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(intBoard.getCell(3, 2)));
		assertTrue(targets.contains(intBoard.getCell(2, 3)));
		assertTrue(targets.contains(intBoard.getCell(0, 3)));
	}



}
