

/**
 * @author Demonna Wade and Erica Manzer
 *
 */
public class Student implements Comparable<Student> {
	
	private String name;
	private int score;
	
	public Student (String studentName, int studentScore)
	{
		name = studentName;
		score = studentScore;
	}

	@Override
	public int compareTo(Student arg0) {
		if (arg0.score >= score) { 
			
		}
		else { 
			
		}
		return 0;
	}

	@Override
	public String toString() {
		return  name +  score ;
	}

	 
	
}
