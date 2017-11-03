package test;

import clueGame.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class PlayerTest2 {

	@Test
	public void completingDeckTest() {
		Set<Card> deck = new HashSet<Card>();
		deck = getDeck();
		
		// Testing the size of the deck
		assertEquals(21, deck.size());
		int person, weapon, room = 0;
		for ( Card card : deck )
		{
			if ( card.getCardType() == CardType.PERSON)
			{
				person++;
			}
			if ( card.getCardType() == CardType.ROOM)
			{
				room++;
			}
			if ( card.getCardType() == CardType.WEAPON)
			{
				weapon++;
			}
		}
		// Testing that the correct number of cards for each kind exist
		assertEquals(6, person);
		assertEquals(6, weapon);
		assertEquals(9, room);
		
		// Testing to see if a particular card exist
		
	}

}
