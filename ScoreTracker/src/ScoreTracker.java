//Authors: Demonna Wade and Erica Manzer

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ScoreTracker {

	private static ArrayList<Student> list = new ArrayList<Student>();  
	private String[] files = {"scores.txt", "badscores.txt", "nofile.txt"};
	static String line = "";

	public void loadDataFromFile(String fileName) throws FileNotFoundException  
	{ 


		String name = ""; 
		String firstName = "";
		String lastName = ""; 
		String stringScore = ""; 
		int score; 

		list.clear();

		FileReader reader = new FileReader(fileName);
		Scanner in = new Scanner(reader);
		while (in.hasNext())
		{
			
			firstName = in.next();
			lastName = in.next();
			stringScore = in.next();

			try {

				score = Integer.parseInt(stringScore); 				
				String fullName = firstName + " " + lastName + " "; 
				Student temp = new Student(fullName, score); 
				list.add(temp);		

			} 
			catch (NumberFormatException a) 
			{
				name = firstName + " " + lastName + " ";
				line = "Incorrect format for " + name + "not a valid score: " + stringScore;  
				System.out.println();
				System.out.println(line);
				System.out.println();
				
			}

		} 

		in.close();
	}




	public void printInOrder() {
		Collections.sort(list);
		for (Student temp: list) {
			System.out.println(temp);
		}

	}

	public void processFiles() {
		ScoreTracker object = new ScoreTracker();
		for (int i = 0; i < 3; i++) 
		{
			String temp = files[i];
			try {

				object.loadDataFromFile(temp);
				object.printInOrder();
			}
			catch (FileNotFoundException e)
			{
				System.out.println();
				line = "Can't open file: " + temp; 
				System.out.println(line);
			}		
		}
	}

	public static void main (String[] args ) 
	{
		System.out.println();
		ScoreTracker object = new ScoreTracker(); 
		object.processFiles(); 

	}





}


