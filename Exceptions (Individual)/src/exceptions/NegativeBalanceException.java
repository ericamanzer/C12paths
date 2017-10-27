package exceptions;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class NegativeBalanceException extends Exception {
	private static double negativeBalance;
	NegativeBalanceException()
	{
		super("Error: negative balance");
	}
	NegativeBalanceException(double negBalance)
	{
		super("Amount exceeds balance by " + negBalance + "." );
		negativeBalance = negBalance;
		//write the message to a log file
		try
		{
			File file = new File ("logfile.txt");
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.println("Amount exceeds balance by " + negBalance + "." );
			printWriter.close();
		}
		catch(FileNotFoundException e)
		{
			e.getLocalizedMessage();
		}
	}
	@Override  //toString method for writing negativeBalance
	public String toString() {
		return "Balacne of " + negativeBalance + " not allowed";
	}
}
