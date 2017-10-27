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

	
	@Test
	public void testRandomMovementKangaroo() {
		
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		
		for (int i=1;i<=5; i++)
			pieces[i] = new Doormat(' ');

		for (int i=7; i<=11; i++)
			pieces[i] = new Doormat(' ');

		for (int i=14; i<20; i++)
			pieces[i] = new Doormat(' ');
		
		Kangaroo kangaroo = new Kangaroo('K', 6);
		pieces[6] = kangaroo;
		int count = 0;
		kangaroo.move(pieces, 8);
		
		if ( 6 != kangaroo.getLocation()) 
		{
			count++;
		}
		// Ensure each option is randomly chosen some number of times. 
		System.out.println(kangaroo.getLocation());
		assert(count > 0);	
	}
	
	@Test
	public void testRandomMovementGiraffe() {
		
		Drawable [] pieces = new Drawable[GameEngine.BOARD_SIZE];
		
		for (int i=1;i<=5; i++)
			pieces[i] = new Doormat(' ');

		for (int i=7; i<=11; i++)
			pieces[i] = new Doormat(' ');

		for (int i=14; i<20; i++)
			pieces[i] = new Doormat(' ');
		
		Giraffe giraffe = new Giraffe('K', 6);
		pieces[6] = giraffe;
		int count = 0;
		
		giraffe.move(pieces, 8);
		
		if ( 6 != giraffe.getLocation()) 
		{
			count++;
		}
		// Ensure each option is randomly chosen some number of times. 
		System.out.println(giraffe.getLocation());
		assert(count > 0);	
	}
	

}
