//Authors: Demonna Wade, Erica Manzer
import java.util.Arrays;

public class Station {
	public String stationName;
	public final int MAX_HIRE = 5;
	Detective[] arr = new Detective [MAX_HIRE];
	/**
	 * @param name
	 * @param badgeNumber
	 * @param station
	 * @param arr
	 */
	public Station(String station) {
		this.stationName = station;
	}
	
	@Override
	public String toString() {
		return "Station [stationName=" + stationName + ", MAX_HIRE=" + MAX_HIRE + ", arr=" + Arrays.toString(arr) + "]";
	}
	
	
	
}
