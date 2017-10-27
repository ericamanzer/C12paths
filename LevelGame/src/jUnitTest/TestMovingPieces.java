/**
 * 
 */
package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import levelPieces.Kangaroo;
import levelPieces.Giraffe;
import levelPieces.Doormat;


/**
 * @author Demonna Wade & Erica Manzer
 *
 */
public class TestMovingPieces {

	/*
	 * Tests random motion, used by both the Sniper and Unicorn.
	 * Strategy: 
	 * - Place pieces in all spaces EXCEPT 0, 6, 12, 13, 20.
	 * - Ensures both end spots (0 and 20) are open.
	 * - Same piece is used in all spaces, as piece identity doesn't matter.
	 * - Set player location to space 13.
	 * - Call move function many times, ensure each open space is chosen
	 *   (13 is "open" but has the player, so it should NOT be chosen)
	 */
	
	@Test
	public void testRandomMovement() {
		// Each test will create its own list of pieces
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		// Start with 1, leaves 0 open
		for (int i=1;i<=5; i++)
			pieces[i] = new Doormat(' ');
		// Leave 6 open
		for (int i=7; i<=11; i++)
			pieces[i] = new Doormat(' ');
		// Leave 12, 13 and 20 open, assume player in 13
		for (int i=14; i<20; i++)
			pieces[i] = new Doormat(' ');
		// Place Sniper in an open space - 6
		// Note that Sniper location will be updated via move method, 
		// so occasionally location 6 will be open and may be chosen
		Kangaroo kangaroo = new Kangaroo('K');
		pieces[6] = kangaroo;
		int count = 0;
		Drawable [] returnBoard = new Drawable[GameEngine.BOARD_SIZE];
		returnBoard = kangaroo.getPieces();
		for (int i=0; i<21; i++) {
			kangaroo.move(pieces, i);
			if (pieces[i] != returnBoard[i]) count++;
			System.out.println(pieces[i] + "= " + returnBoard[i]);
			
		}
		// Ensure each option is randomly chosen some number of times. 
		//System.out.println(count);
		assert(count > 1);	
	}
	

}
