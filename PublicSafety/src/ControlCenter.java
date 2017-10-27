/*
Authors: Demonna Wade, Erica Manzer
 * ControlCenter class
 * Used as part of the PublicSafety exercise
 */
import java.util.ArrayList;

public class ControlCenter {
	// You'll learn about ArrayList soon
	private ArrayList<PublicSafety> offices;
	
	public ControlCenter() {
		offices = new ArrayList<PublicSafety>();
	}
	
	// In a real system we would prompt for office names. To simplify
	// grading and testing, we will hard code names and the sequence 
	// of hires in the following methods

	public void createOffices() {
		offices.add(new PublicSafety("CSM", "Golden"));
		offices.add(new PublicSafety("CU", "Boulder"));
	}
	
	public void doHiring() {
		// Hire from several stations to show the effect on badge number
		PublicSafety office1 = offices.get(0);
		PublicSafety office2 = offices.get(1);
		/*
		 *  Using an enumerated type would be better - we'll learn that soon 
		 *  For now, the PublicSafety class should have a doHire method. 
		 *  The parameter to doHire is a boolean. 
		 *  - If true, tell the city station to hire one detective. 
		 *  - If false, tell the university station to hire one detective.
		 */
		
		office1.doHire(true);
		office1.doHire(false);
		office1.doHire(true);
		office2.doHire(false);
		office2.doHire(true);
		office1.doHire(true);
		
		// Now we do a loop to show that stations ensure they don't hire more
		// than the max # of detectives. See figure 1 in assignment writeup.
		for (int i=4; i<=6; i++)
			office1.doHire(true);	
	}
	
	public void printAllDetectives() {
		System.out.println("\nPrinting All Detectives");
		for (PublicSafety office : offices) 
			office.printDetectiveLists();
	}

	public static void main(String[] args) {
		ControlCenter control = new ControlCenter(); 
		control.createOffices();
		control.doHiring();
		control.printAllDetectives();
	}

}
