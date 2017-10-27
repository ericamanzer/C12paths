//Authors: Demonna Wade, Erica Manzer
public class Detective {
	public String name;
	public int badgeNumber;
	
	/**
	 * @param name
	 * @param badgeNumber
	 */
	public Detective(String name, int badgeNumber) {
		super();
		this.name = name;
		this.badgeNumber = badgeNumber;
	}
	
	public Detective() {
		super();
		name = ""; 
		badgeNumber = 0; 
	}

	@Override
	public String toString() {
		return "Detective [name=" + name + ", badgeNumber=" + badgeNumber + "]";
	}
	
	
	
	
}
