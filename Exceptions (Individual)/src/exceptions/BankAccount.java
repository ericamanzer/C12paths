package exceptions;

public class BankAccount {
	private static double balance;

	BankAccount (double value)
	{
		balance = value;
	}
	
	public void withdraw (double withdrawAmount) throws NegativeBalanceException
	{
		if (withdrawAmount > balance)
		{
			double negBalance = balance - withdrawAmount;
			throw new NegativeBalanceException(negBalance);
		}
		else if (withdrawAmount <= balance)
		{
			balance = balance - withdrawAmount;
		}
	}
	
	public void quickWithdraw (double withdrawAmount) throws NegativeBalanceException
	{
		if (withdrawAmount > balance)
		{
			throw new NegativeBalanceException();
		}
		else
		{
			balance = balance - withdrawAmount;
		}
	}

}
