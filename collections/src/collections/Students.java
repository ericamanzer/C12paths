// Authour : Demonna Wade


/*
 * TreeMap Results {
 * I don't this much changed. With the various names, everything still printed \
 * }
 * 
 * Map {
 * Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
	Cannot instantiate the type Map<String,String>

	at collections.Students.<init>(Students.java:20)
	at collections.Students.main(Students.java:56)
	
	}
 * 
 */
package collections;

import java.util.*;

public class Students {
	
	private Map<String, String> courses;
	public Students()
	{
		courses = new Map<String, String>();
	}
	
	public void getCourses()
	{
		Scanner in = new Scanner(System.in);
		String name = "";
		String course = "";
		do
		{
			System.out.print("Enter a student or 'Q' to quit: ");
			name = in.next();
			if (!(name.equalsIgnoreCase("Q")))
			{
				courses.put(course, name);
			}
			else 
			{
				return;
			}
			System.out.print("Enter the student's favorite course or 'Q' to quit: ");
			course = in.next();
		} while (!(name.equalsIgnoreCase("Q")));
	}
	
	public void printStudents()
	{
		for (String value: this.courses.keySet())
		{
			String course = value.toString();
			String name = courses.get(course).toString();
			System.out.println(name + " likes " + course);
		}
	}

	public static void main(String[] args) {
		Students students = new Students();
		students.getCourses();
		students.printStudents();
	}

}
