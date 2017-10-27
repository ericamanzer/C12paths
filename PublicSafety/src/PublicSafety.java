//Authors: Demonna Wade, Erica Manzer
import java.util.Arrays;
import java.util.Scanner; 

public class PublicSafety{
	public Station city;
	public Station university;
	
	public int cCount = 0, uCount = 0;
	public static int badgeCount = 1;

	
	//String name, int badgeNumber, String station, Detective[] arr,
	public PublicSafety( String university, String city) {
		this.city = new Station(city);
		this.university = new Station(university);
	}
	



	@Override
	public String toString() {
		return "PublicSafety [city=" + city + ", university=" + university + ", cCount=" + cCount + ", uCount=" + uCount
				+ ", badgeCount=" + badgeCount + "]";
	}

	public void doHire(boolean ans)
	{
	
		if (ans == true) { 
			if (cCount < city.MAX_HIRE) {
				
				Scanner scan = new Scanner(System.in); 
				System.out.println("Enter City detective name"); 
				Detective dec = new Detective();
				dec.name = scan.nextLine();
				dec.badgeNumber = badgeCount; 
				city.arr[cCount] = dec;
				//System.out.println(city.arr);
				//System.out.println(university.arr);
				badgeCount++;
				cCount++;
			}
			else {
				System.out.println("Not enough badges."); 
			}
		}
		else {
			if (uCount < university.MAX_HIRE) {
				
				
				
				Scanner scan = new Scanner(System.in); 
				System.out.println("Enter University detective name"); 
				Detective dec = new Detective();
				dec.name = scan.nextLine();
				dec.badgeNumber = badgeCount;
				university.arr[uCount] = dec;
				//System.out.println(city.arr);
				//System.out.println(city.arr.);
				//System.out.println(university.arr);
				badgeCount++;
				uCount++; 
				
			}
			else {
				System.out.println("Not enough badges."); 
			}
		}
		
		
	}
	
	
	public void printDetectiveLists() { 
		System.out.println("List pf dectectives for " + city.stationName);
		for (int i = 0; i < cCount; i++){
			System.out.println("Detective [Badge = " + city.arr[i].badgeNumber + ", Name = " + city.arr[i].name + "]" );
		}
		System.out.println();
		System.out.println("List pf dectectives for " + university.stationName);
		for (int i = 0; i < uCount; i++){
			System.out.println("Detective [Badge = " + university.arr[i].badgeNumber + ", Name = " + university.arr[i].name + "]" );
		}
		System.out.println();
	}
	
	
}
 