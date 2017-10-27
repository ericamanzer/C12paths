// Authors: Demonna Wade & Erica Manzer
import java.util.Random; 

public class PetOwner extends Person { //PetOwner is situation 2 
	// Random value is called and used to print the corresponding place in the question or answer array
	
	private String myName;	
	private String occupation;
	private String pet; 
	private String petName; 
	private String[] question = {"Do you like cats?", "Do you like dogs?"};
	private String[] answers = {"Let me tell you about my cat Mittens", "Let me tell you about my dog named Eddi", "My duck's name is Daschel"}; 
	
	
	public PetOwner (String name, String job, String pet, String petName) // Constructor: sets name, job, pet, and petName
	{
		myName = name;
		occupation = job;
		this.pet = pet; 
		this.petName = petName; 
	}

	public void askName() // Ask for name  
	{
		String ask = "What is your name? ";
		System.out.println(ask);
	}
	
	public void giveName() // Prints the name 
	{
		System.out.println(myName);
	}
	
	public void whatDoYouDo() // Prints job 
	{
		System.out.println(occupation);
	}
	
	public void askQuestion() // Asks questions 
	{
		Random rand = new Random(); 
		int n = rand.nextInt(2); 
		
		System.out.println(question[n]);
	}
	
	public void answerQuestion() // Answers questions 
	{
		Random rand = new Random(); 
		int n = rand.nextInt(3);
		
		System.out.println(answers[n]);
	}
	
	
}
