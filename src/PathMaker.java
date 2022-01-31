import java.io.*;
import java.util.*;

public class PathMaker {
	private static Map<Character, FlightMap> allLoc;    //location pointing to FlightMap data
	private static Map<Character, Integer> visited;    //visitation status and associated total cost
	private static Queue<Character> next;    //traversal status
	
	/**
	 * Assumes file exists, no error in formatting, no particular order in printing destinations, 
	 * no pretty printing, and no optimization in paths found (cost-wise). Paths found by BFS.
	 * 
	 * @param args first the input filename, then the output destination
	 */
	private static void main(String[] args) {
		Character start;
		allLoc = new HashMap<Character, FlightMap>();
		visited = new HashMap<Character, Integer>();
		next = new ArrayDeque<Character>();
		try (Scanner reader = new Scanner(new File(args[0]))) {    //try block parses
			start = reader.next().charAt(0);    //saves initial location, not safe
			while (reader.hasNext()) {    //dangerous if wrong formatting
				Character loc1 = reader.next().charAt(0);
				allLoc.put(loc1, new FlightMap(loc1));    //saves 2 locations
				Character loc2 = reader.next().charAt(0);
				allLoc.put(loc2, new FlightMap(loc2));
				Integer cost = Integer.parseInt(reader.next());    //then an associated cost
				allLoc.get(loc1).add(loc2, cost);
			}
		}
		catch (FileNotFoundException fnf) {
			System.out.println("Invalid file.");
		}
		catch (IOException io) {
			System.out.print("Formatting error.");
		}
		
		args[1];
	}
}
