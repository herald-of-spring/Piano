import java.io.*;
import java.util.*;

public class SearchMap {
	private static Map<Character, FlightMap> allLoc;    //location pointing to FlightMap data
	private static Map<Character, Integer> visited;    //visitation status and associated total cost
	private static Map<Character, ArrayList<Character>> path;    //notes the path taken to get to certain location
	private static Queue<Character> next;    //traversal status
	
	/**
	 * Assumes file exists, no error in formatting, no particular order in printing destinations, 
	 * no pretty printing, and no optimization in paths found (cost-wise). Paths found by BFS.
	 * 
	 * @param args first the input filename, then the output destination
	 */
	public static void main(String[] args) {
		Character start = null;
		allLoc = new HashMap<Character, FlightMap>();
		visited = new HashMap<Character, Integer>();
		next = new ArrayDeque<Character>();
		path = new HashMap<Character, ArrayList<Character>>();
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
		bfs(start);
		try (PrintWriter writer = new PrintWriter(new File(args[1]))) {    //output writing
			writer.println("Destination" + '\t' + "Flight Route from " + start + '\t' + "Total Cost");
			for (Character location : path.keySet()) {
				writer.println(location + '\t' + path.get(location).toString() + '\t' + "$" + visited.get(location));
			}
		}
		catch (IOException io) {
			System.out.println("Error in writing file.");
		}
		
	}
	
	/**
	 * Traverses all possible locations, noting travel history and compounding costs, not optimized for cheapest cost
	 * 
	 * @param start the starting node for traversal
	 */
	@SuppressWarnings("unchecked")
	public static void bfs(Character start) {
		next.add(start); 
		visited.put(start, 0);
		ArrayList<Character> temp = new ArrayList<Character>();
		temp.add(start);    //only has starting point, where the path length will grow
		path.put(start, temp);
		while (!next.isEmpty()) {    //start BFS, with some (not recommended) shortcuts 
			Character curr = next.poll(); 
			Integer cost = visited.get(curr); 
			Set<Character> locList = allLoc.get(curr).next(); 
			for (Character nextLoc : locList) { 
				if (!visited.containsKey(nextLoc)) { 
					next.add(nextLoc);
					visited.put(nextLoc, cost+allLoc.get(curr).cost(nextLoc));    //adds the cost of previous node and edge cost
					ArrayList<Character> newPath = (ArrayList<Character>)path.get(curr).clone();
					newPath.add(nextLoc);    //old path + new location
					path.put(nextLoc, newPath);
				} 
			} 
		}
		visited.remove(start);
		path.remove(start);    //primer for easier writing to file
	}
}
