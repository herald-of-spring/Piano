import java.util.*;

public class FlightMap {
	private Map<Character, Integer> destMap;
	private Character name;
	
	/**
	 * Constructor to create a node equivalent.
	 * 
	 * @param name name of location, character type
	 */
	public FlightMap(Character name) {
		this.name = name;
		destMap = new HashMap<Character, Integer>();
	}
	
	/**
	 * Connects each node together (one-directionally) with their associated costs.
	 * 
	 * @param dest destination name, character type
	 * @param cost cost of traversing this path, integer type
	 */
	public void add(Character dest, Integer cost) {
		destMap.put(dest, cost);
	}
	
	/**
	 * Provides a list for graph traversal.
	 * 
	 * @return A set of characters listing available next destinations
	 */
	public Set<Character> next() {
		return destMap.keySet();
	}
	
	/**
	 * Returns the cost to add into itinerary.
	 * 
	 * @param dest destination name, must be connected (i.e found in next()), character type
	 * @return The cost of traversing this edge
	 */
	public Integer cost(Character dest) {
		return destMap.get(dest);
	}
	
	/**
	 * Gets name
	 * 
	 * @return Name of location
	 */
	public Character getName() {
		return name;
	}
}
