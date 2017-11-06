package clueGame;

public class Solution {

	private String person, room, weapon; 
	
	public void setAnswerKeyPerson (String answerPerson)
	{
		this.person = answerPerson;
	}
	
	public void setAnswerKeyRoom ( String answerRoom )
	{
		this.room = answerRoom;
	}
	
	public void setAnswerKeyWeapon ( String answerWeapon )
	{
		this.weapon = answerWeapon;
	}
	
	public String getPerson() {
		return person; 
	}
	
	public String getWeapon() {
		return weapon; 
	}
	
	public String getRoom() {
		return room; 
	}
	
}
