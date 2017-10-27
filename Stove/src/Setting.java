/**
 * Enum Setting
 */

/**
 * @author Demonna Wade and Erica Manzer
 *
 */
// enum Setting to be used for the Burner class
public enum Setting {

	OFF("---"), LOW("--+"), MEDIUM("-++"), HIGH("+++");

	private String value;
	
	Setting (String aVal)
	{
		value = aVal;
	}
	
	//toString function for displaying values
	public String toString()
	{
		return value;
	}
	
};



