
/*
 * 
 * Authors: Demonna Wade and Erica Manzer 
 * 
 */
package clueGame;

public class Card {

	private String cardName; 
	private CardType cardType; 
	
	public Card()
	{
		this.cardName = "";
		this.cardType = CardType.PERSON;
	}
	
	
	public Card(String carName, CardType cardType) {
		super();
		this.cardName = carName;
		this.cardType = cardType;
	}

	/*
	public boolean equals() {
		return true; // FIXME !!!
	}
	*/
	
	public CardType getCardType()
	{
		return this.cardType;
	}
	
	public String getCardname()
	{
		return this.cardName;
	}
	
}
