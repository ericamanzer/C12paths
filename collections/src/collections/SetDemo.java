
package collections;
import java.util.*;

public class SetDemo {
	private Set<String> animals;
	public SetDemo() {
		animals = new TreeSet<String>();
	}

	public void getAnimals() {
		Scanner in = new Scanner(System.in);
		String animal = "";
		do 
		{
			System.out.print("Enter an animal or Q to quit: ");
			animal = in.next();
			if (!(animal.equalsIgnoreCase("Q")))
				animals.add(animal);							
		} while (!(animal.equalsIgnoreCase("Q")));
	}

	public void printAnimals() {
		for (String animal : animals)
			System.out.println(animal);
	}	
	
	public static void main(String[] args) {
		SetDemo sd = new SetDemo();
		sd.getAnimals();
		sd.printAnimals();
	}

}
