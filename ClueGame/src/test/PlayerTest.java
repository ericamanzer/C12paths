package test;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;
import org.junit.Test;
import clueGame.*;

public class PlayerTest {
	private static Board board;

	@BeforeClass
	public static void setup() throws Exception
	{
		board = Board.getInstance();
		//set the file names to be used 
		board.setConfigFiles("C14 Layout.csv", "C12 Layout.txt");
		board.setWeaponsConfigFile("WeaponsConfig.txt");
		board.setPeopleConfigFile("PeopleConfig.txt");
		
		board.initialize();
	}

	@Test
	public void testLoadingPeople() {
		int peopleSize = board.getComputerPlayers().size() + board.getHumanPlayer().size(); 
		Set<HumanPlayer> tempHuman = new HashSet<HumanPlayer>(); 
		tempHuman = board.getHumanPlayer(); 
		Set<ComputerPlayer> tempComputer = new HashSet<ComputerPlayer>(); 
		tempComputer = board.getComputerPlayers(); 
		assertEquals(6, peopleSize); 
		for (HumanPlayer human : tempHuman)
		{
			if (human.getPlayerName().equals("CompSci"))
			{
				//System.out.println("works");
				assertTrue(true);
			}
		}
		int found = 0;
		for (ComputerPlayer comput : tempComputer)
		{
			if (comput.getPlayerName().equals("MechE"))
			{
				assertTrue(true);
				found++;
			}
			
			else if (comput.getPlayerName().equals("ChemE"))
			{
				found ++;
			}
			else if (comput.getPlayerName().equals("Mining"))
			{
				found ++;
			}
			else if (comput.getPlayerName().equals("Geology"))
			{
				found ++;
			}
			else if (comput.getPlayerName().equals("Physics"))
			{
				found ++;
			}
			
		}
		assertEquals(5, found); 
		

	} 

	@Test
	public void completingDeckTest() {
		Set<Card> deck = new HashSet<Card>();
		// add to a deck for testing 
		
		for (Card card : board.getWeaponsPile())
		{
			deck.add(card);
			//System.out.println("weaponsPile");
		}
		for (Card card : board.getPeoplePile())
		{
			deck.add(card);
			//System.out.println("peoplePile");
		}

		for (Card card : board.getRoomPile())
		{
			deck.add(card);
			//System.out.println("roomPile");
		}
		
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
 
	@Test 
	public void testDealCardsOut() {
		
		int deckSize = board.getDeck().size(); 
		//int keySize = board.getKey().size(); 

		assertEquals(0, deckSize); 
		//assertEquals(3, keySize);
		for (HumanPlayer player: board.getHumanPlayer())
		{
			assertEquals(3, player.getMyCardSize());
			System.out.println(player.getMyCardSize());
		}
		for (ComputerPlayer player: board.getComputerPlayers())
		{
			System.out.println("yo");
			assertEquals(3, player.getMyCardSize());
			
		}

	}



}
