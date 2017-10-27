// Authors: Demonna Wade & Erica Manzer
public class Philosopher extends Person { //Philosopher is situation 1 
	// This situation uses a counter that is static and will increase every time it is used. 
	//The counter will be modded by the number of questions or answers and will print that value  
	
	
	private String myName;	
	private String occupation;
	private String[] question = {"Can you really experience anything objectively?", "Why is there something rather than nothing?", "What's your favorite color?"};
	private String[] answers = { "Of course, post-modernism is meh", "Life has no meaning", "I think therfore I am"}; 
	private static int counter = 0; 
	
	public Philosopher (String name, String job) // constructor: sets name and job
	{
		myName = name;
		occupation = job;
	}
 
	public void askName() //Asks for name 
	{
		String ask = "What is your name? ";
		System.out.println(ask);
	}
	
	public void giveName() //Prints name
	{
		System.out.println(myName);
	}
	
	public void whatDoYouDo() //Prints job
	{
		System.out.println(occupation);
	}
	
	public void askQuestion() //Prints questions
	{
		System.out.println(question[counter % 3]);
		counter++; 
	}

	public void answerQuestion() // Prints question 
	{	
		System.out.println(answers[counter % 3]);
		counter++; 
	}
	

	
}
