// Authors: Demonna Wade & Erica Manzer
public class Introvert extends Person { // Introvert class 
	// The introvert class always asks the same questions and uses the same answer

	private String myName;	
	private String occupation;
	private String[] question = {"Can you really experience anything objectively?", "Why won't you leave me alone?", "Do you like to ski?", "Why is there something rather than nothing?", "What's your favorite color?"};
	private String[] answers = {"I want to be alone.", "Snowmass and Steamboat are my favorite resorts.", "Of course, post-modernism is meh"}; 
	
	
	public Introvert (String name, String job) // Constructor class sets name and jobs  
	{
		myName = name;
		occupation = job;
	}

	public void askName() // Asks name 
	{
		String ask = "What is your name? ";
		System.out.println(ask);
	}
	
	public void giveName() // Prints name
	{
		System.out.println(myName);
	}
	
	public void whatDoYouDo() // Prints job
	{
		System.out.println(occupation);
	}
	
	public void askQuestion() // Prints same question everytime function is called 
	{
		System.out.println(question[2]);
	}
	
	public void answerQuestion() // Prints same answer everytime function is called 
	{
		System.out.println(answers[1]);
	}
	
}
