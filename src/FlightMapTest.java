import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.*;

class FlightMapTest {
	private FlightMap a = new FlightMap('A');
	private FlightMap b = new FlightMap('B');
	private FlightMap c = new FlightMap('C');
	private FlightMap d = new FlightMap('D');
	private FlightMap e = new FlightMap('E');
	/**
	 * Environment setup.
	 * <p>
	 * ...A<-E
	 * <p>
	 * ../|^.|
	 * <p>
	 * .v.v.\v
	 * <p>
	 * C->B->D
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		a.add('B', 200);
		a.add('C', 400);
		b.add('D', 1000);
		c.add('B', 500);
		d.add('A', 700);
		e.add('A', 100);
		e.add('D', 900);
	}

	/**
	 * Tests node connectivity (on A).
	 */
	@Test
	public void nextTest() {
		Set<Character> ans = new HashSet<Character>();
		ans.add('B');
		ans.add('C');
		assertEquals(ans, a.next());
	}
	
	/**
	 * Tests itinerary cost (from A to C, from A to D).
	 * Overhead algorithm of BFS, not taking into account costs.
	 */
	@Test
	public void costTest() {
		assertEquals(400, a.cost('C'));
		assertEquals(200+1000, a.cost('B')+b.cost('D'));
	}
}
