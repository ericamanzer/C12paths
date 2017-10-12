package clueGame;

public class BadConfigFormatException extends RuntimeException {
	// Instructions told to leave as skeleton and requires a constructor
	
	public static String message = "Bad Configuration Format Exception";
	
	public BadConfigFormatException()
	{
		super(message); 
	}
	
	public BadConfigFormatException(String m) 
	{
		super(m); 
	}
}
