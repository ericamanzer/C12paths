package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import levelPieces.Parrot;

public class TestInteractions {
	
	@Test
	public void testValE() {
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Parrot parrot = new Parrot('e',10);
		pieces[10] = parrot;
		
		assertEquals(InteractionResult.HIT, parrot.interact(pieces, 10));
		
		for (int i=0; i<10; i++)
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
		for (int i=11; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
	}
	
	@Test
	public void testValO() {
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Parrot parrot = new Parrot('o', 10);
		pieces[10] = parrot;
		
		assertEquals(InteractionResult.ADVANCE, parrot.interact(pieces, 10));
		
		for (int i=0; i<10; i++)
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
		for (int i=11; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
	}
	
	@Test
	public void testValAsterisk() {
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Parrot parrot = new Parrot('*', 10);
		pieces[10] = parrot;
		
		assertEquals(InteractionResult.KILL, parrot.interact(pieces, 10));
		
		for (int i=0; i<10; i++)
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
		for (int i=11; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
	}
	
	@Test
	public void testValT() {
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Parrot parrot = new Parrot('T', 11);
		pieces[10] = parrot;
		
		assertEquals(InteractionResult.GET_POINT, parrot.interact(pieces, 10));
		
		for (int i=0; i<10; i++)
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
		for (int i=11; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
	}	
	
	@Test
	public void testValW() {
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		Parrot parrot = new Parrot('w', 8);
		pieces[10] = parrot;
		
		assertEquals(InteractionResult.GET_POINT, parrot.interact(pieces, 10));
		
		for (int i=0; i<10; i++)
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
		for (int i=11; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, parrot.interact(pieces, i));
	}		

}
