package exceptions;

public class ATM {
	
	private BankAccount myAccount;
	
	ATM ()
	{
		myAccount = new BankAccount(500);
	}
	
	public void handleTransactions ()
	{
		//within a try-catch, call withdraw
		try 
		{
			myAccount.withdraw(600);
		}
		//catch has two printouts
		catch (NegativeBalanceException e)
		{
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {

		//create ATM object and call handleTransactions
		ATM atm = new ATM();
		atm.handleTransactions();
	}

}
