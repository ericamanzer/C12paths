package test;

import clueGame.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class PlayerTest2 {
	private static Board board;
	@Test
	public void completingDeckTest() {
		Set<Card> deck = new HashSet<Card>();
		deck = board.getDeck();
		
		// Testing the size of the deck
		assertEquals(21, deck.size());
		int person = 0;
		int room = 0;
		int weapon = 0;
		int exist = 0;
		for ( Card card : deck )
		{
			if ( card.getCardType() == CardType.PERSON)
			{
				person ++;
			}
			if ( card.getCardType() == CardType.ROOM)
			{
				room ++;
			}
			if ( card.getCardType() == CardType.WEAPON)
			{
				weapon ++;
			}
		}
		// Testing that the correct number of cards for each kind exist
		assertEquals(6, person);
		assertEquals(6, weapon);
		assertEquals(9, room);
		
		// Testing to see if a particular card exist
		if ( person >= 1 && room >= 1 && weapon >= 1)
		{
			exist ++;
		}
		assertEquals(1, exist);
		
	}

}
